package com.example.countrysearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @Nullable
    public final T data;

    @NonNull
    public final Status status;

    @Nullable
    public final String message;

    public Resource(@Nullable T data, @NonNull Status status, @Nullable String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static <T> Resource<T> loading(@Nullable T data) {
      return new Resource<>(data,Status.LOADING,null);
    }

    public static <T> Resource<T> error(@NonNull String message , @Nullable T data) {
        return new Resource<>(data,Status.ERROR,message);
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(data,Status.SUCCESS,null);
    }

    public enum Status {ERROR, LOADING, SUCCESS}

}
