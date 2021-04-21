package com.franktran.jsp.enrolment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EnrolmentService {

  private final EnrolmentRepository enrolmentRepository;

  public EnrolmentService(EnrolmentRepository enrolmentRepository) {
    this.enrolmentRepository = enrolmentRepository;
  }

  public List<Enrolment> getAllEnrolments() {
    return enrolmentRepository.findAll();
  }

  public Enrolment getEnrolmentById(long id) {
    return enrolmentRepository.findById(id).orElse(null);
  }

  public Enrolment createEnrolment(Enrolment enrolment) {
    return enrolmentRepository.save(enrolment);
  }

  public Enrolment updateEnrolment(long id, Enrolment enrolment) {
    Enrolment existEnrolment = enrolmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Enrolment with id %s does not exist", id)));
    if (Objects.nonNull(enrolment.getCourse().getId())
        && !Objects.equals(existEnrolment.getCourse().getId(), enrolment.getCourse().getId())
        && Objects.nonNull(enrolment.getSemester())
        && !Objects.equals(existEnrolment.getSemester(), enrolment.getSemester())) {
      Optional<Enrolment> enrolmentByStudentId = enrolmentRepository.findEnrolmentByCourseIdAndSemester(enrolment.getStudent().getId(), enrolment.getSemester());
      if (enrolmentByStudentId.isPresent()) {
        throw new IllegalArgumentException("Course with semester taken");
      }
      existEnrolment.getCourse().setId(enrolment.getCourse().getId());
      existEnrolment.setSemester(enrolment.getSemester());
    }
    return enrolmentRepository.save(existEnrolment);
  }

  public void deleteEnrolment(long id) {
    boolean existsById = enrolmentRepository.existsById(id);
    if (!existsById) {
      throw new IllegalArgumentException(String.format("Enrolment with id %s not exists", id));
    }
    enrolmentRepository.deleteById(id);
  }
}
