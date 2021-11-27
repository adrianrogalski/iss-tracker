package com.example.isstracker.repository;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public class ApiRepository<T> extends ApiRepositoryConnect<T> implements SimpleGenericRepository {

    public ApiRepository(Class modelClass) {
        super(modelClass);
    }

    @Override
    public Optional<T> findByURI(URI uri) throws InterruptedException, IOException {
        return get(uri);
    }
}
