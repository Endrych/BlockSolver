package cz.blocksolver.backend.schema;

import java.io.File;

public interface ISchemaLoader {
    Schema loadSchema(File file);
}
