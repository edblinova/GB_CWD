import datetime

def show_notes(pb: dict) -> None:
    print()
    pb = {k: v for k, v in sorted(pb.items(), key=lambda item: datetime.datetime.strptime(item[1][0], "%d.%m.%Y %H:%M:%S"))}
    for i, note in pb.items():
        print(f'{i:>3}. Дата-время: {note[0]} Заметка: {note[1]}')
    print()


def main():
    print('\nКнига с заметками:\n')
    show_notes({1: [datetime.datetime.now().strftime("%d.%m.%Y %H:%M:%S"), 'Text']})

if __name__ == '__main__':
    main()
