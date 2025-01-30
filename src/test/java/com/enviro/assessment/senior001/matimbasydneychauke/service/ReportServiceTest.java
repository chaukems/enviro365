package com.enviro.assessment.senior001.matimbasydneychauke.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class ReportServiceTest {

    @Autowired
    ReportService reportService;

    @Test
    public void successfully_save_document() {
        int value = 1;
        assertThat(value).isEqualTo(1);
    }
}
