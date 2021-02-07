package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.SpeakerJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SpeakerTest {
    @Autowired
    private SpeakerJpaRepository jpaRepository;

    @Test
    public void testFind() {
        Speaker speaker = jpaRepository.getOne(1L);
        assertNotNull(speaker);
    }

    @Test
    @Transactional
    public void testSaveAndGetAndDelete() {
        Speaker s = new Speaker();
        s.setCompany("Pluralsight");
        s.setFirstName("Dan");
        s.setLastName("Bunker");
        s.setTitle("Author");
        s.setSpeakerBio("Consulting and mentoring");
        s = jpaRepository.saveAndFlush(s);

        Speaker otherSpeaker = jpaRepository.getOne(s.getSpeakerId());
        assertEquals("Dan", otherSpeaker.getFirstName());

        jpaRepository.deleteById(otherSpeaker.getSpeakerId());
    }

}
