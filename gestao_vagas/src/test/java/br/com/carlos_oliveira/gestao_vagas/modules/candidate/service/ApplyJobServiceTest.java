package br.com.carlos_oliveira.gestao_vagas.modules.candidate.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.carlos_oliveira.gestao_vagas.exceptions.JobNotFoundException;
import br.com.carlos_oliveira.gestao_vagas.exceptions.UserNotFoundException;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.ApplyJobEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.model.CandidateEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.carlos_oliveira.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.carlos_oliveira.gestao_vagas.modules.company.model.JobEntity;
import br.com.carlos_oliveira.gestao_vagas.modules.company.repository.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobServiceTest {

    @InjectMocks
    private ApplyJobService applyJobService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {

        try {
            applyJobService.create(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }

    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found() {

        var candidateId = UUID.randomUUID();
        var candidateEntity = new CandidateEntity();
        candidateEntity.setId(candidateId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidateEntity));

        try {
            applyJobService.create(candidateId, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }

    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void should_be_able_to_create_a_new_apply_job() {

        var candidateId = UUID.randomUUID();

        var jobId = UUID.randomUUID();

        var applyJobEntity = ApplyJobEntity.builder()
                .id(UUID.randomUUID())
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(any(ApplyJobEntity.class))).thenReturn(applyJobEntity);

        var result = applyJobService.create(candidateId, jobId);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());

    }

}
