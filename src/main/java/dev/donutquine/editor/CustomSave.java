package dev.donutquine.editor;

import java.nio.file.Path;

import dev.donutquine.swf.Export;
import dev.donutquine.swf.SupercellSWF;
import dev.donutquine.swf.exceptions.UnableToFindObjectException;
import dev.donutquine.swf.movieclips.MovieClipOriginal;
import dev.donutquine.swf.textures.SWFTexture;

public class CustomSave {
    public static void saveSWF(SupercellSWF swf, boolean preferLowres, Path filepath) {
        String filename = filepath.getFileName().toString();
        String basename = filename.substring(0, filename.lastIndexOf('.'));

        SwfReassembler reassembler = new SwfReassembler();
        for (Export export : swf.getExports()) {
            MovieClipOriginal movieClip;
            try {
                movieClip = swf.getOriginalMovieClip(export.id(), export.name());
            } catch (UnableToFindObjectException e) {
                throw new RuntimeException(e);
            }

            int newId = reassembler.addMovieClip(movieClip, swf);

            reassembler.addExport(newId, export.name());
        }

        SupercellSWF reassembledSwf = reassembler.getSwf();
        if (reassembledSwf.getExports().isEmpty()) {
            System.err.println("No matched exports found");
            return;
        }

        for (int i = 0; i < swf.getTextureCount(); i++) {
            SWFTexture texture = swf.getTexture(i);
            reassembledSwf.addTexture(texture);
        }

        reassembler.recalculateIds();

        Path directory = filepath.toAbsolutePath().getParent();
        directory.toFile().mkdirs();
        String outputFilepath = directory.resolve(basename + ".sc").toString();

        reassembledSwf.save(outputFilepath, null);
    }
}
