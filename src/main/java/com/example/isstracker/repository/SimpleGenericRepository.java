package com.example.isstracker.repository;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public interface SimpleGenericRepository<T> {
    Optional<T> findByURI(URI uri) throws InterruptedException, IOException;
}
