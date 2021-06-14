package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userID}")
   Note[] getNotesForUser(Integer userID);


    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId}")
    Note getNote(Integer noteId);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNote(Integer noteId);

    @Update("UPDATE NOTES SET notetitle = #{title}, notedescription = #{description} WHERE noteid = #{noteId}")
     void updateNote(Integer noteId, String title, String description);

    @Select("SELECT * FROM NOTES")
    Note[] getNoteListings();


    @Insert("INSERT INTO NOTES(notetitle, notedescription, userid) " +
    "VALUES(#{NoteTitle}, #{NoteDescription}, #{UserID})")
    @Options(useGeneratedKeys = true,keyProperty = "NoteID")
      int insert(Note note);

}
