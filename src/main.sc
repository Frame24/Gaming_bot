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
                go: /Game over
            
            state: Right
                q: * *прав* *
                a: Думаешь, раз выбрал наПРАВо, то ты ПРАВ? А вот и нет! Провались в яму! Game over!
                go: /Game over
            
            state: Straight
                q: * *прям* *
                a: Перед вами сундук. Что будете делать?
                
                state: Open
                    q: * (~сломать|~открывать|~вскрывать) *
                    a: Вам выпали монеты, ровно {{ getRandomInt(10) }}
                
                    
        state: NoMelon
            event: noMatch
            a: На эльфийском, друг!
    
    state: Game over
        a: Сыграть снова?
        
        state: Yes
            q: * Да *
            go: /Start
        
        state: No
            q: * Нет *
            a: Игра окончена...
        
    state: NoMatch
        event!: noMatch
        a: Попробуйте сказать это на эльфийском.