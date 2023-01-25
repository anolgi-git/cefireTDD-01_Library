package org.cefire.library;

import org.junit.jupiter.api.Test;

public class BookCollectionTest {

    @Test
    public void ShouldFindBookByISBN(){
        BookCollection books = new BookCollection(new Book[]{
                new Book("un-isbn-1","un título 1","un autor 1"),
                new Book("un-isbn-2","un título 2","un autor 2"),
                new Book("un-isbn-2","un título 2","un autor 2"),
                new Book("un-isbn-3","un título 3","un autor 3")
        });

        if(!books.find("un-isbn-2").isEmpty()){
            boolean allCoincidencesAreCorrect = true;
            for (Book book : books.find("un-isbn-2")) {
                if(!book.getISBN().equals("un-isbn-666")){
                    allCoincidencesAreCorrect = false;
                    break;
                }
            }

            if(allCoincidencesAreCorrect){
                System.out.println("CORRECT ✓ --> Should find a Book by ISBN");
                return;
            }
            System.out.println("ERROR ⛌ --> Should find a Book by ISBN");
        }
    }
}
