package org.example.chapter_06.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Extractor<R> {
    R run(PreparedStatement statement) throws SQLException;
}
