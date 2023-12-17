from find_note import find_note
from show_notes import show_notes

import datetime

def change_note(pb: dict) -> str:
    result = find_note(pb)
    show_notes(result)
    uid = int(input('Введите ID заметки, которую хотите изменить: '))
    c_date, c_note = pb[uid]
    new_note = input('Введите новый текст заметки: ')
    pb[uid] = [c_date, new_note]
    return new_note if new_note else c_note

def main():
    note = change_note({1: [datetime.datetime.now().strftime("%d.%m.%Y %H:%M:%S"), 'Text']})
    print(f'\nЗаметка {note} успешно сохранена!\n')

if __name__ == '__main__':
    main()
