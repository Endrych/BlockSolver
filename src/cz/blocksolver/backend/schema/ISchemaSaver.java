package cz.blocksolver.backend.schema;

import java.io.File;

public interface ISchemaSaver {
    void saveSchema(File file, Schema schema);
}
