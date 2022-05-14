package org.example.chapter_06.db;

import java.sql.SQLException;

interface With<P> {
    void run(P stmt) throws SQLException;
}
