import datetime

def save_file(pb: dict = {}, path: str = 'note_book.txt') -> None:
    data = []
    for note in pb.values():
        note = ';'.join(note)
        data.append(note)
    data = '\n'.join(data)
    with open(path, 'w', encoding='UTF-8') as file:
        file.write(data)


def main():
    save_file({1: [datetime.datetime.now().strftime("%d.%m.%Y %H:%M:%S"), 'Text']})
    print('\nКнига с заметками успешно сохранена!\n')

if __name__ == '__main__':
    main()
