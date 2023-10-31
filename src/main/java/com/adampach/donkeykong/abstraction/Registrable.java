package com.adampach.donkeykong.abstraction;

import java.util.function.Consumer;

public interface Registrable <T>
{
    void Register(Consumer<T> registerCallback);
    void Unregister(Consumer<T> unregisterCallback);
}
