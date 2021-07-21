# Mini2DGame
This is a game project. it has 2D graphics and labyrinth based design.  The goal is arrive to finish point before capturing by enemy. 

##Development
Check "Rapor.pdf" file where in the "Results and Outputs" folder if you want to know things about development.

##Disclaimer
This game does not contain commercial interests.The reason of creating the game is learning java development.

##Game
Game 
	-->There is a hero who wants to capture the cup and there is one/two enemy who want to catch you.
	-->You get damage if you catch by enemy.
Goal of game
	--> Capturing the cup before die.
Game ending conditions
	-->Game will be over if you don't have any hitpoint.
	-->you will be win if you reached the cup.
Controllin keys
	-->Control the hero through direction arrows.
GamePlay

![GamePlayGif](https://user-images.githubusercontent.com/44205116/126494677-60a57483-5cdf-4485-8ac7-137236411e2a.gif)

##Game Customizing
You can change enemy character and enemy's starting point with following instructions where below:
	1-)Go "Project Source" directory.
	2-)find "Harita.txt" file
	3-)Open it and write firts 1 or 2  line this:
		Karakter:<CharacterName>,Kapi:<Door>
		
		Example:
		Karakter:Stormtrooper,Kapi:D


Options for <door> (Door is the starting point of enemy character)
	-->A
	-->B
	-->C
	-->D
	-->E
Options for <CharacterName>
	-->"DarthVader"
	-->"Stormtrooper"
	-->"KyloRen"


You can change map with following instructions where below:
	1-)Go "Project Source" directory.
	2-)find "Harita.txt" file
	3-)You can write new map matrix after defining the enemy(s) hero.
		--> 1 means free areas to move
		--> 0 means obstacle
