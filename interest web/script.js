
function showInfo(type) {
    const infoDisplay = document.getElementById('info-display');
    let infoContent = '';

    if (type === 'formula1') {
        infoContent = 
        `
            <h2>Formula 1</h2>
            <p>As the highest class of international racing for single-seater formula racing cars, 
                Formula 1 is the pinnacle of motorsport and the world’s most prestigious motor racing competition. There really is nothing like it.
                It’s a team sport (it needs to be to change all 4 tyres on a car in under 2 seconds!), 
                but the drivers are more like fighter pilots than sportspeople. Battling extreme g-forces, 
                making daring decisions in the blink of an eye – and at 370km/h. To be the best, F1 drivers push themselves – and their incredibly innovative machines – to the very limit.</p>
            
        `;
    } else if (type === 'Calendar') {
        infoContent = `
            <h2>2024 Formula 1 Calendar</h2>
            <p>1. Bahrain Grand Prix - Bahrain International Circuit, Sakhir - Feb 29 - Mar 2</p>
            <p>2. Saudi Arabian Grand Prix - Jeddah Street Circuit, Jeddah - Mar 7-9</p>
            <p>3. Australian Grand Prix - Albert Park Circuit, Melbourne - Mar 22-24</p>
            <p>4. Japanese Grand Prix - Suzuka Circuit, Suzuka - Apr 5-7</p>
            <p>5. Chinese Grand Prix - Shanghai International Circuit, Shanghai - Apr 19-21</p>
            <p>6. Miami Grand Prix - Miami International Autodrome, Miami - May 3-5</p>
            <p>7. Emilia Romagna Grand Prix - Autodromo Enzo e Dino Ferrari, Imola - May 17-19</p>
            <p>8. Monaco Grand Prix - Circuit de Monaco, Monaco - May 24-26</p>
            <p>9. Canadian Grand Prix - Circuit Gilles Villeneuve, Montreal - Jun 7-9</p>
            <p>10. Spanish Grand Prix - Circuit de Barcelona-Catalunya, Barcelona - Jun 21-23</p>
            <p>11. Austrian Grand Prix - Red Bull Ring, Spielberg - Jun 28-30</p>
            <p>12. British Grand Prix - Silverstone Circuit, Silverstone - Jul 5-7</p>
            <p>13. Hungarian Grand Prix - Hungaroring, Budapest - Jul 19-21</p>
            <p>14. Belgian Grand Prix - Circuit de Spa-Francorchamps, Spa - Jul 26-28</p>
            <p>15. Dutch Grand Prix - Circuit Zandvoort, Zandvoort - Aug 23-25</p>
            <p>16. Italian Grand Prix - Monza Circuit, Monza - Aug 30 - Sep 1</p>
            <p>17. Azerbaijan Grand Prix - Baku City Circuit, Baku - Sep 13-15</p>
            <p>18. Singapore Grand Prix - Marina Bay Street Circuit, Singapore - Sep 20-22</p>
            <p>19. United States Grand Prix - Circuit of the Americas, Austin - Oct 18-20</p>
            <p>20. Mexican Grand Prix - Autódromo Hermanos Rodríguez, Mexico City - Oct 25-27</p>
            <p>21. Brazilian Grand Prix - Interlagos Circuit, Sao Paulo - Nov 1-3</p>
            <p>22. Las Vegas Grand Prix - Las Vegas Street Circuit, Las Vegas - Nov 21-23</p>
            <p>23. Qatar Grand Prix - Lusail International Circuit, Lusail - Nov 29 - Dec 1</p>
            <p>24. Abu Dhabi Grand Prix - Yas Marina Circuit, Yas Marina - Dec 6-8</p>

        `;
    } else if (type === 'Verstappen') {
        infoContent = 
        `
            <h2>Favorite Driver</h1>
            <h2>Biography</h2>
            <p>He’s Max by name, and max by nature.
            Arriving as Formula 1’s youngest ever competitor at just 17 years old, Verstappen pushed his car, 
            his rivals and the sport’s record books to the limit. The baby-faced Dutchman with the heart of a lion 
            took the Toro Rosso – and then the Red Bull – by the horns with his instinctive racing style.</p>
            <p>
            F1’s youngest points scorer soon became its youngest race winner – at the age of 18 years and 228 days – 
            with an opportunistic but controlled drive on debut for Red Bull in Barcelona 2016. A true wheel-to-wheel 
            racer, another stunning drive in Brazil from the back of the pack to the podium on a treacherous wet track 
            kept the plaudits coming. </p>
            <p>
            Verstappen’s no-holds-barred attitude and hard defending have sometimes 
            landed him in hot water with his peers and paymasters. But the mistakes that initially marred his potential 
            have given way to maturity, while the bravado and energy that make him a blockbuster talent have remained – 
            and the victories have kept on coming. </p>
            <p>
            They led to his first F1 drivers’ crown after that now legendary, final-round showdown with Lewis Hamilton 
            in 2021. He followed that up with a powerhouse title defence in 2022, before an epic third successive 
            championship triumph, featuring a record 19 wins from 23 Grands Prix.</p>
            <p>
            The son of former F1 driver Jos Verstappen and super-quick karting Mum Sophie Kumpen, racing runs 
            through his genes. Despite moving out of Dad’s house to live in Monaco, Verstappen remains close to his family, and though he’s not afraid to speak his mind, he can still be surprisingly shy.
            </p>
            <p>
            Having become the Netherlands' first world champion aged just 24, the expectations for the new 
            generation’s leading light are sky high – but with Verstappen there’s a feeling that the sky’s the limit.</p>
        
        `;
    }else if (type === 'RB') {
        infoContent = `
            <h2>Favorite Team</h2>
            <img src = "redbull.png" width = "300">
            <h2>Redbull Racing</h2>
            <p>Red Bull were no strangers to F1 - as sponsors - prior to formally entering as a works team in 2004. 
                Nonetheless, the scale of their success over the following decade was staggering. 
                After a first podium in 2006, the team hit their stride in 2009, claiming six victories and second 
                in the constructors' standings. Over the next four seasons they were a tour de force, claiming 
                consecutive title doubles between 2010 and 2013, with Sebastian Vettel emerging as the sport's youngest 
                quadruple champion. Now they are recapturing that glory with an equally exciting talent – one named Max Verstappen…</p>
            <p>Click here to know more about <a href="https://www.redbull.com/int-en/redbullracing">Redbull Racing</a></p>
           
        `;
    }

    infoDisplay.innerHTML = infoContent;
}
