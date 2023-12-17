# Дополнить телефонный справочник возможностью изменения и удаления данных. 
# Пользователь также может ввести имя или фамилию, 
# и Вы должны реализовать функционал для изменения и удаления данных.


import logging

from open_file import open_file
from save_file import save_file
from show_notes import show_notes
from create_note import create_note
from find_note import find_note
from change_note import change_note
from delete_note import delete_note


def main():

    menu = '''Главное меню
            1. Открыть заметки
            2. Сохранить заметки
            3. Показать все заметки
            4. Создать заметку
            5. Найти заметку
            6. Изменить заметку
            7. Удалить заметку
            8. Выход'''

    try:
        note_book = {}
        
        while True:
            print(menu)

            choice = input('Выберите пункт меню: ')
            
            match choice:
                case '1':
                    note_book = open_file()
                    print('\nКнига с заметками успешно загружена!\n')
                case '2':
                    save_file(note_book)
                    print('\nКнига с заметками успешно сохранена!\n')
                case '3':
                    print('\nКнига с заметками:\n')
                    show_notes(note_book)
                case '4': 
                    note = create_note(note_book)
                    print(f'\Заметка {note} успешно создана!\n')
                case '5':
                    note = find_note(note_book)
                    show_notes(note)
                case '6':
                    note = change_note(note_book)
                    print(f'\Заметка {note} успешно сохранена!\n')
                case '7':
                    note = delete_note(note_book)
                    print(f'\Заметка {note} успешно удалена!\n')
                case '8':
                    print('До свидания, всего хорошего!')
                    break
                case _:
                    print('Ошибка ввода! Выберите пункт меню от 1 до 8')
    
    except Exception as error:
        logging.warning(f'Произошла ошибка при выполнении кода: {error}')


if __name__ == '__main__':
    main()
