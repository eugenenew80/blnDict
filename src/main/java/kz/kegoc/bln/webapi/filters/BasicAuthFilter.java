package kz.kegoc.bln.webapi.filters;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import kz.kegoc.bln.ejb.SessionContext;
import kz.kegoc.bln.entity.adm.Func;
import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.service.adm.UserService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RMapCache;
import org.apache.commons.codec.binary.Base64;

@Provider
@PreMatching
public class BasicAuthFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext ctx) throws IOException {
		if (ctx.getUriInfo().getPath().contains("auth"))
			return;

		if (StringUtils.isEmpty(ctx.getHeaderString("Authorization")))
			throw new NotAuthorizedException("NOT AUTHORIZED");

		if (!ctx.getHeaderString("Authorization").startsWith("Basic "))
			throw new NotAuthorizedException("BASIC AUTHORIZATIN ONLY");

		String authHeader=  ctx.getHeaderString("Authorization").substring(6);
		String[] auth = new String(Base64.decodeBase64(authHeader), "UTF-8").split(":");
		String userName = auth[0];
		String password = auth[1];

		if (StringUtils.isEmpty(userName))
			throw new NotAuthorizedException("EMPTY USER");

		String hash = Base64.encodeBase64String((userName + ":" + password).getBytes());
		User user = sessions.get(hash);
		if (user==null)
			throw new NotAuthorizedException("USER IS NOT REGISTERED");

		sessions.put(userName, user,30, TimeUnit.MINUTES);

		ctx.setSecurityContext(
			new SecurityContext() {
				@Override
				public boolean isUserInRole(String role) {
					return true;
				}

				@Override
				public boolean isSecure() {
					return false;
				}

				@Override
				public Principal getUserPrincipal() {
					return new CustomPrincipal(userName, user);
				}

				@Override
				public String getAuthenticationScheme() {
					return null;
				}
			}
		);
	}

	private void checkAccess(SessionContext context, String func, ContainerRequestContext ctx) {
		String operation = "";
		switch (ctx.getMethod()) {
			case "POST":
				operation = "VREATE";
				break;

			case "PUT":
				operation = "UPDATE";
				break;

			case "GET":
				operation = "SELECT";
				break;

			case "DELETE":
				operation = "DELETE";
				break;
		}

		User user = userService.findById(context.getUser().getId(), context);
		List<Func> funcs = user.getRoles().stream()
			.flatMap(u -> u.getRole().getFuncs().stream())
			.map(roleFunc -> roleFunc.getFunc())
			.distinct()
			.collect(Collectors.toList());

		boolean b = funcs.stream()
				.filter(it -> it.getCode().equals(func))
				.findFirst().isPresent();

		if (!b) throw new NotAuthorizedException("ACCESS DENIED");
	}


	@Inject
	private RMapCache<String, User> sessions;

	@Inject
	private UserService userService;
}
