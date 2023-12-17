def open_file(path: str = 'note_book.txt') -> dict:
    note_book = {}    
    
    with open(path, 'r', encoding='UTF-8') as file:
        notes = file.readlines()
    
    for i, note in enumerate(notes, 1):
        note_book[i] = note.strip().split(';')
    
    return note_book


def main():
    note_book = open_file()
    print(note_book)

if __name__ == '__main__':
    main()
