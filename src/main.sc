require: slotfilling/slotFilling.sc
  module = sys.zb-common
require: functions.js

theme: /
    state: Start
        q!: $regex</start>
        a: Молви друг и войди!
        script:
            $session.coins_amount = 0;
            $session.temp_coins = 0;
        
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
                    script:
                        $session.temp_coins = getRandomInt(10);
                        $session.coins_amount = Number($session.coins_amount) + Number($session.temp_coins);
                    a: Вам выпали монеты, ровно {{ $session.temp_coins }} монет
                    a: В данный момент у вас {{ $session.coins_amount }} монет
                    
                    a: Потратить монеты?
                    
                    state: Yes
                        q: * Да *
                        a: На что же потратились все монеты...
                        script:
                            $session.coins_amount = 0
                        a: В данный момент у вас {{ $session.coins_amount }} монет
                        go!: /Start
                    
                    state: No
                        q: * Нет *
                        a: Вам повезло! Количество монет удвоилось!
                        script: 
                            $session.coins_amount *= 2
                        a: В данный момент у вас {{ $session.coins_amount }} монет
                    
                
                    
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