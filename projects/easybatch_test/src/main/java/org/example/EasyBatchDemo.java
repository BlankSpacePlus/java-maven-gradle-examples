package org.example;

import org.easybatch.core.filter.HeaderRecordFilter;
import org.easybatch.core.job.Job;
import org.easybatch.core.job.JobBuilder;
import org.easybatch.core.job.JobExecutor;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.writer.FileRecordWriter;
import org.easybatch.flatfile.DelimitedRecordMapper;
import org.easybatch.flatfile.FlatFileRecordReader;
import org.easybatch.xml.XmlRecordMarshaller;
import org.example.entity.Person;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EasyBatchDemo {

    public static void main(String[] args) throws IOException, JAXBException {
        Path inputFile = Paths.get("src/main/resources/persons.csv");
        Path outputFile = Paths.get("src/main/resources/persons.xml");
        Job job = new JobBuilder()
                .reader(new FlatFileRecordReader(inputFile))
                .filter(new HeaderRecordFilter())
                .mapper(new DelimitedRecordMapper<>(Person.class, "id", "name"))
                .marshaller(new XmlRecordMarshaller<>(Person.class))
                .writer(new FileRecordWriter(outputFile))
                .batchSize(10)
                .build();
        JobExecutor jobExecutor = new JobExecutor();
        JobReport report = jobExecutor.execute(job);
        System.out.println("Finish!");
        jobExecutor.shutdown();
    }

}
