package ca.sheridancollege.pate2406.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CommentData {
	private Long cid;
	@NonNull
	private String namethread;
	@NonNull
	private String contentthread;
	
}
