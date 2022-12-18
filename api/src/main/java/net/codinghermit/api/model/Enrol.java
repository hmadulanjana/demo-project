package net.codinghermit.api.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Enrol implements Serializable {

	// private long enrolId;
  private long courseId;
	private long studentId;
}
