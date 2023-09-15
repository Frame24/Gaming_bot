require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: functions.js

theme: /

    state: Start
        q!: $regex</start>
        a: Молви друг и войди!
        
        state: Melon
            q: (melon/мелон)
            a: Перед тобой три коридора. В какой пойдешь?
            
            state: Left
                q: * *лев* *
                a: Тебя сьел дракон. Game over!
                go!: /GameOver
            
            state: Right
                q: * *прав* *
                a: Думаешь, раз выбрал наПРАВо, то ты ПРАВ? А вот и нет! Провались в яму! Game over!
                go!: /GameOver
            
            state: Straight
                q: * *прям* *
                a: Перед вами сундук. Что будете делать?
                
                state: Open
                    q: * (~сломать|~открывать|~вскрывать) *
                    a: Вам выпали монеты, ровно {{ getRandomInt(10) }}
                    a: В данный момент у вас {{ coins_count }} монет
                
                    
        state: NoMelon
            event: noMatch
            a: На эльфийском, друг!
    
    state: GameOver
        a: Сыграть снова?
        
        state: Yes
            q: * Да *
            go!: /Start
        
        state: No
            q: * Нет *
            a: Игра окончена...
        
    state: NoMatch
        event!: noMatch
        a: Попробуйте сказать это на эльфийском.