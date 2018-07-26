package com.haozi.batch.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * person intermediate processor
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person> {
    public static final Logger LOGGER = LoggerFactory.getLogger(PersonItemProcessor.class);


    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        LOGGER.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}