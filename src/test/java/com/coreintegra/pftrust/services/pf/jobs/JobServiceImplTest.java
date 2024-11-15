package com.coreintegra.pftrust.services.pf.jobs;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

class JobServiceImplTest {

    private final Path fileStorageLocation = Paths.get("")
            .toAbsolutePath().normalize();

    @Test
    public void fetchEmployees() {

        Path targetLocation = this.fileStorageLocation.resolve("data-lake-tsp-connector-daimler-fleet-dev1-141-2022-07-15-12-44-45-0952d32b-f4ca-314f-885a-3120662afe3d.gz");


        try {
            FileInputStream fileInputStream = new FileInputStream(targetLocation.toFile());

            byte[] b = new byte[1048576];

            int read = fileInputStream.read(b);

            String decompress = gzipDecompress(b);

            System.out.println(decompress);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String gzipDecompress(byte[] bytes) throws Exception {
        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
        BufferedReader bf = new BufferedReader(new InputStreamReader(gis, StandardCharsets.UTF_8));
        StringBuilder outStr = new StringBuilder();
        String line;
        while ((line=bf.readLine())!=null) {
            outStr.append(line);
        }
        return outStr.toString();
    }

}