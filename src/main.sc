require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: functions.js

theme: /

    state: Start
        q!: $regex</start>
        a: Молви друг и войди!
        
        state: Melon
            q: melon
            a: Перед тобой три коридора. В какой пойдешь?
            
            state: Left
                q: * *лев* *
                a: Тебя сьел дракон. Game over!
            
            state: Right
                q: * *прав* *
                a: Думаешь, раз выбрал наПРАВо, то ты ПРАВ? А вот и нет! Провались в яму! Game over!
            
            state: Straight
                q: * *прям* *
                a: Перед вами сундук. Что будете делать?
                
                state: Open
                    q: * *(~сломать|~открыть|~вскрыть)* *
                    a: Вам выпали монеты, ровно {{ getRandomInt(10) }}
        state: NoMelon
            event: noMatch
            a: На эльфийском, друг!
        
    state: NoMatch
        event!: noMatch
        a: Попробуйте сказать это на эльфийском.