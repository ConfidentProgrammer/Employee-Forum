package ca.sheridancollege.pate2406.database;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.pate2406.beans.CommentData;
import ca.sheridancollege.pate2406.beans.DiscussionData;

@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public void addThread(DiscussionData app) {
		String query = "INSERT INTO threads (name, content, department, threadDate,threadTime) VALUES (:name, :content, :depart ,:tDate, :tTime)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("name", app.getName());
		namedParameters.addValue("content", app.getContent());
		namedParameters.addValue("depart", app.getDepartment());
		namedParameters.addValue("tDate", app.getThreadDate());
		namedParameters.addValue("tTime", app.getThreadTime());

		jdbc.update(query, namedParameters);
	}

	// this method is to fetch all the appointments

	public ArrayList<DiscussionData> getAllthreads() {

		String query = "SELECT id, name, content, department, threadDate,threadTime  FROM threads";
		ArrayList<DiscussionData> list = new ArrayList<DiscussionData>();
		List<Map<String, Object>> rows = jdbc.queryForList(query, new HashMap<String, Object>());
		// System.out.println(rows);

		for (Map<String, Object> row : rows) {
			// Date date = (Date)row.get("threadDate");
			// Time time = (Time)row.get("threadTime");
			list.add(new DiscussionData((Long) row.get("id"), (String) row.get("name"), ((String) row.get("content")),
					((String) row.get("department")), (String) row.get("threadDate"),
					/* date.toLocalDate(),time.toLocalTime() */
					(String) row.get("threadTime")));
		}
		return list;
	}

	// this is to getAppointment By ID
	public void AddCommentDataById(Long id, CommentData cData) {
		String query = "INSERT INTO threadComment (cid,namethread, contentthread) VALUES (:id,:name, :content)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id",id);
		namedParameters.addValue("name", cData.getNamethread());
		namedParameters.addValue("content", cData.getContentthread());
		jdbc.update(query, namedParameters);
	}

	public ArrayList<CommentData> getAllthreadsComment() {

		String query = "SELECT cid, namethread, contentthread FROM threadComment, threads where cid=threads.id";
		ArrayList<CommentData> list = new ArrayList<CommentData>();
		List<Map<String, Object>> rows = jdbc.queryForList(query, new HashMap<String, Object>());
		// System.out.println(rows);

		for (Map<String, Object> row : rows) {
			// Date date = (Date)row.get("threadDate");
			// Time time = (Time)row.get("threadTime");
			list.add(new CommentData((Long) row.get("cid"), (String) row.get("namethread"), ((String) row.get("contentthread"))));
		}
		return list;
	}
}
