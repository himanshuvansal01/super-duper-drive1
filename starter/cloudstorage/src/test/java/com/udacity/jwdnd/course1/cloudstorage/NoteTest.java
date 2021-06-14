package com.udacity.jwdnd.course1.cloudstorage;



import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTest extends CloudStorageApplicationTests {

    @Test
    public void TestDelete(){
        String NoteTitle = "My first note";
        String NoteDescription = "I have typed my first note.";
        HomePage homePage = SignUpAndLogin();
        CreateNote(NoteTitle,NoteDescription,homePage);
        homePage.NavToNotesTab();
        homePage = new HomePage(webDriver);
        Assertions.assertFalse(homePage.NoNotes(webDriver));
        DeleteNote(homePage);
        Assertions.assertTrue(homePage.NoNotes(webDriver));
    }


    private void DeleteNote(HomePage homePage) {

        homePage.DeleteNote();
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickOk();
    }

    @Test
    public void TestCreateAndDisplay(){
        String NoteTitle = "My second note";
        String NoteDescription = "I have typed my second note.";
        HomePage homePage = SignUpAndLogin();
        CreateNote(NoteTitle, NoteDescription, homePage);
        homePage.NavToNotesTab();
        homePage = new HomePage(webDriver);
        Note note = homePage.getFirstNote();
        Assertions.assertEquals(NoteTitle,note.getNoteTitle());
        Assertions.assertEquals(NoteDescription, note.getNoteDescription());
        DeleteNote(homePage);
        homePage.logout();

    }

    @Test
    public void TestModify(){
        String NoteTitle = "My first note";
        String NoteDescription = "I have typed my first note.";
        HomePage homePage = SignUpAndLogin();
        CreateNote(NoteTitle,NoteDescription,homePage);
        homePage.NavToNotesTab();
        homePage = new HomePage(webDriver);
        homePage.EditNote();
        String ModifiedNoteTitle = "My second note";
        homePage.ModifyNoteTitle(ModifiedNoteTitle);
        String ModifiedNoteDescription = "I have typed my second note.";
        homePage.ModifyNoteDescription(ModifiedNoteDescription);
        homePage.saveNoteChanges();
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickOk();
        homePage.NavToNotesTab();
        Note note = homePage.getFirstNote();
        Assertions.assertEquals(ModifiedNoteTitle, note.getNoteTitle());
        Assertions.assertEquals(ModifiedNoteDescription, note.getNoteDescription());

    }

    @Test
    private void CreateNote(String noteTitle, String noteDescription, HomePage homePage) {
        homePage.NavToNotesTab();
        homePage.addNewNote();
        homePage.setNoteTitle(noteTitle);
        homePage.setNoteDescription(noteDescription);
        homePage.saveNoteChanges();
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickOk();
        homePage.NavToNotesTab();
    }








}