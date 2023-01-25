package org.cefire.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookCollection {
    private final List<Book> books;

    public BookCollection(Book[] books) {
        this.books = Arrays.asList(books);
    }

    public List<Book> find(String textToMatch) {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : this.books) {
            if(book.getISBN().equals(textToMatch) || book.getTitle().contains(textToMatch)){
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> getBooks() {
        return books;
    }
}
