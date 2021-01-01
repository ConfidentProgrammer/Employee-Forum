package ca.sheridancollege.pate2406.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionData implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String content;
	private String department;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String threadDate;
	//@DateTimeFormat(pattern = "HH:mm")
	private String threadTime;
}
