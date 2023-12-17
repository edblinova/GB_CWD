import datetime

def create_note(pb: dict) -> str:
    note = input('Введите текст заметки: ')
    if len(pb)==0:
        uid = 1
    else:
        uid = max(pb.keys())+1
    pb[uid] = [datetime.datetime.now().strftime("%d.%m.%Y %H:%M:%S"), note]
    return note


def main():
    note = create_note({1: [datetime.datetime.now().strftime("%d.%m.%Y %H:%M:%S"), 'Text']})
    print(f'\nЗаметка {note} успешно создана!\n')

if __name__ == '__main__':
    main()
