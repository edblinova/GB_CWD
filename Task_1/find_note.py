from show_notes import show_notes
import datetime

def find_note(pb: dict) -> dict:
    result = {}
    word = input('Введите ключевое слово для поиска: ').lower()
    for i, note in pb.items():
        if word in note[1].lower():
            result[i] = note
    return result


def main():
    note = find_note({1: [datetime.datetime.now().strftime("%d.%m.%Y %H:%M:%S"), 'Text']})
    show_notes(note)

if __name__ == '__main__':
    main()
