package br.com.carlos_oliveira.gestao_vagas.modules.company.controller;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.carlos_oliveira.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.carlos_oliveira.gestao_vagas.modules.company.model.CompanyEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.CompanyRepository;
import br.com.carlos_oliveira.gestao_vagas.utils.TestUtils;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JobControllerTest {

        @Value("${security.token.secret}")
        private static String secretKey;

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext context;

        @Autowired
        private CompanyRepository companyRepository;

        @Before
        public void setup() {
                mvc = MockMvcBuilders
                                .webAppContextSetup(context)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Test
        public void should_be_able_to_create_a_new_job() throws Exception {

                var companyEntity = CompanyEntity.builder()
                                .description("COMPANY_DESCRIPTION")
                                .email("EMAIL@COMPANY.COM")
                                .password("1234567890")
                                .username("COMPANY_USERNAME")
                                .name("COMPANY_NAME")
                                .build();

                companyEntity = companyRepository.saveAndFlush(companyEntity);

                var createJobDTO = CreateJobDTO.builder()
                                .benefits("BENEFITS_TEST")
                                .description("DESCRIPTION_TEST")
                                .level("LEVEL_TEST")
                                .build();

                mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtils.objectToJSON(createJobDTO))
                                .header("Authorization",
                                                TestUtils.generateToken(companyEntity.getId(), secretKey)))
                                .andExpect(MockMvcResultMatchers.status().isOk());

        }

        @Test
        public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception {

                var createJobDTO = CreateJobDTO.builder()
                                .benefits("BENEFITS_TEST")
                                .description("DESCRIPTION_TEST")
                                .level("LEVEL_TEST")
                                .build();

                mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtils.objectToJSON(createJobDTO))
                                .header("Authorization",
                                                TestUtils.generateToken(UUID.randomUUID(), secretKey)))
                                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        }
}
