package kz.kegoc.bln.translator;

import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.Lang;

public interface Translator<T extends HasId> {
    T translate(T entity, Lang defLang);
}
