package org.letscareer.letscareer.global.common.utils.redis.service;

abstract class ExpirationService<T> {
    public abstract void setWithExpire(T item);
}
