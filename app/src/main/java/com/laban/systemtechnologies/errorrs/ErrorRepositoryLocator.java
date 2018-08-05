package com.laban.systemtechnologies.errorrs;

public class ErrorRepositoryLocator {
    private static ErrorRepository repository;

    public static void setRepository(ErrorRepository repository) {
        if (ErrorRepositoryLocator.repository == null) {
            ErrorRepositoryLocator.repository = repository;
        }
    }

    public static ErrorRepository getRepository() {
        return repository;
    }

}
