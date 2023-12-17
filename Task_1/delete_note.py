from find_note import find_note
from show_notes import show_notes

import datetime

def delete_note(pb: dict) -> str:
    result = find_note(pb)
    show_notes(result)
    uid = int(input('Введите ID зметки, которую хотите удалить: '))
    c_date, c_note = pb[uid]
    pb = pb.pop(uid)
    return c_note

def main():
    note = delete_note({1: [datetime.datetime.now().strftime("%d.%m.%Y %H:%M:%S"), 'Text']})
    print(f'\nЗаметка {note} успешно удалена!\n')

if __name__ == '__main__':
    main()
