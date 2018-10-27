var x = true;
var backpack = [];
var choice1 = ["Swim up", "Go through bubble", "Use pickaxe", "Go to the submarine", "Jump", "Open the box", "Go through left door", "Take rusty key", "Take the golden key", "Use rusty key"];
var choice2 = ["Explore the castle", "Try to dig through the debris", "Use thermite", "Go through left door", "Climb", "Don't open the box", "Use glowstick", "Go up", "Reach through bubble", "Go back", "Use blue key"];
var choice3 = ["", "Go through the right door", "Use thermite", "Use gold key"];
var storyStart = "You float slowly down to the bottom of the ocean. Apparently, trying to light a signal fire on top of your life raft wasn't the greatest of ideas. Luckily your fancy futuristic scuba gear can let you breathe underwater for at least a day, so you might not drown if you can get back to land fast enough. Eventually you land on what appears to be a tower of a castle. Which is weird. There are stairs on the tower leading down into the castle. Explore the castle or try to swim back up?";
var storyStartChoiceSwim = "You try to swim back up, but you're way too deep down to get anywhere near the surface, so you float back down and go into the castle anyways.";
var storyStartChoiceExplore = "You go down the stairs.";
var story1 = "As you walk down the stairs, the stairs crumble underneath you and you fall. The fall doesn't matter because you're underwater though, so you just float down. At the bottom of the staircase, you find two doors. One door is blocked by debris, and one looks like it's covered by some kind of opaque bubble. Go through bubble door or try to dig through the debris?";
var story1ChoiceBubble = "You walk through the bubble, hoping there's not something that wants to eat you on the other side. As you walk through, you step on a sharp rock, puncturing your scuba suit. You expect water to rush into your suit, but nothing happens.";
var story1ChoiceDebris = "You attempt to pull the debris apart and puncture one of the gloves of your scuba suit, letting water rush in. You back up and accidentally fall through the bubble on the other door, and the water suddenly stops flowing into your suit.";
var story2 = "There must be air on this side of the bubble instead of water. On the floor is a roll of waterproof duct tape, a pickaxe, and a grenade-looking thing that says 'Underwater Thermite' on it. Other than that, the room is empty. You use the duct tape to patch up your suit. Do you want to use the thermite or the pickaxe to break through the debris?";
var story2ChoicePick = "You spend at least a solid hour digging through the debris, eventually breaking through enough of it to squeeze through to the other side.";
var story2ChoiceThermite = "You lodge the thermite into the debris, activate it, and run back through the bubble. You hear the thermite burn through the debris. You walk back through the bubble to find the debris (ironically) completely demolished. You walk right through the newly opened door.";
var story3 = "On the other side of the door you see a small submarine and two doors, one to the right of the sub and one to the left. Try to open the sub, go through right door, or go through the left door?";
var story3ChoiceSub = "You try to open the sub's door, but it's locked. You'll have to find a key to open it up. It's likely your only way out of here. Go through the right door or the left door?";
var story3Left = "You walk towards the left room to find that it has another bubble covering it. You walk through to the air-filled space to find an abyss with a golden box on the other side. You might be able to jump across or climb the walls to get to the other side.";
var story3LeftChoiceJump = "You take a running start, jump, and miss. But you don't fall. There seems to be some kind of invisible platform covering the gorge. Add that to the list of strange things you've encountered today. You get up and jump over to the golden box.";
var story3LeftChoiceClimb = "You climb the walls like Nathan Drake and get over to the other side without falling.";
var story4Left = "Open the golden box?";
var story4LeftChoiceOpen = "You open the golden box to find nothing. Nothing but the floor shaking, the bubble on the door popping, water filling the room, and a giant eel coming through the abyss eating you, that is.";
var story4LeftChoiceNo = "You look behind the likely-booby-trapped box and find a bunch of fancy high-powered glowsticks. You take them and go back to the room with the submarine.";
var story5Right = "You walk through the right door, but you can't see a thing in there. You'll need to use something to light up the place.";
var story6RightChoiceThermite = "You set the thermite down on the floor and turn it on, lighting up the room in a fiery red glow.";
var story6RightChoiceGlowstick = "You crack the glowstick and it lights up the room in a warm blue light.";
var story7Right = "In the light you can see a door high up on the wall in front of you and a door to your left at your level. Both have bubbles over them. Go through left door or swim up to the top door?";
var story7RightLeft = "You walk through the left door and see a small square on the wall in front of you with a bubble over it. There's also a rusty key on the floor right in front of your feet. Reach through the bubble or take the rusty key?";
var story7RightLeftRust = "You take the rusty key and go back through the door.";
var story7RightLeftBubble = "You reach into the bubble hoping there aren't any piranhas on the other side. You feel a key and pull it through to find that it's a blue key marked 'sub'. You take it and pick up the rusty key on your way out to the previous room.";
var story8RightUp = "You swim to the top door and go through it to see a statue of a dragon with something shiny in its mouth. Upon closer observation, you see that the object is a golden key with some kind of markings on it. Take the key or go back?";
var story8RightUpKey = "You grab the key and pocket it without anything horrible happening.";
var story9 = "You go back to the sub. Which key do you use?";
var story9Bad = "You put the key in the lock and turn it, only for it to snap, leaving parts of it inside the keyhole so that you can't try another key. You've ruined your only chance of making it back to the surface.";
var story9Good = "You put the key in the lock and turn it, opening the door to an airlock. You get in, close the door, empty out the airlock of water, and go through to the controls of the submarine. You turn on the sub and drive it up to the surface and back to the coast. It's good to finally be back.";
var story9Gold = "You put the key in the lock and turn it, but the sub's door doesn't open. Instead, the wall at the front of the room rolls down, revealing a giant robotic manta ray. You get into it and pilot it back to the coast, wondering why it and all the other weird stuff was in there."; //Insert Bourne theme here
var storyNumber = 0;
var randomNumber = Math.floor((Math.random() * 100) + 1);

function nextchoice1() {
	var text = document.getElementById('story');
	var choiceOne = document.getElementById('choice1');
	var choiceTwo = document.getElementById('choice2');
	var choiceThree = document.getElementById('choice3');
	var Number = document.getElementById('Number');
	var img = document.getElementById('pic')
	Number.innerHTML = storyNumber;
	if (storyNumber === 0) {
		text.innerHTML = storyStartChoiceSwim + " " + story1;
		choiceOne.innerHTML = choice1[1];
		choiceTwo.innerHTML = choice2[1];
		choiceThree.innerHTML = choice3[0];
		img.src = "CaptureFinished.jpg";
		storyNumber = 1;
	}
	else if (storyNumber === 1) {
		text.innerHTML = story1ChoiceBubble + " " + story2;
		choiceOne.innerHTML = choice1[2];
		choiceTwo.innerHTML = choice2[2];
		choiceThree.innerHTML = choice3[0];
		img.src = "Items.jpg";
		storyNumber = 2;
	}
	else if (storyNumber === 2) {
		text.innerHTML = story2ChoicePick + " " + story3;
		choiceOne.innerHTML = choice1[3];
		choiceTwo.innerHTML = choice2[3];
		choiceThree.innerHTML = choice3[1];
		backpack[0] = "Thermite";
		img.src = "2doorsFinished.jpg";
		storyNumber = 3;
	}
	else if (storyNumber === 3) {
		text.innerHTML = story3ChoiceSub;
		choiceOne.innerHTML = choice3[0];
		choiceTwo.innerHTML = choice2[3];
		choiceThree.innerHTML = choice3[1];
		img.src = "hatch.jpg";
		storyNumber = 4;
	}
	else if (storyNumber === 4 || storyNumber === 8) {
		text.innerHTML = story3Left;
		choiceOne.innerHTML = choice1[4];
		choiceTwo.innerHTML = choice2[4];
		choiceThree.innerHTML = choice3[0];
		img.src = "LeftDoor.png";
		storyNumber = 5;
	}
	else if (storyNumber === 5) {
		text.innerHTML = story3LeftChoiceJump;
		choiceOne.innerHTML = choice1[5];
		choiceTwo.innerHTML = choice2[5];
		choiceThree.innerHTML = choice3[0];
		img.src = "goldbox.jpg";
		storyNumber = 7;
	}
	else if (storyNumber === 7) {
		text.innerHTML = story4LeftChoiceOpen;
		choiceOne.innerHTML = choice3[0];
		choiceTwo.innerHTML = choice3[0];
		choiceThree.innerHTML = choice3[0];
		storyNumber = 115;
		img.src = "eelfail.jpg";
	}
	else if (storyNumber === 16) {
		if (randomNumber > 25) {
			text.innerHTML = story9Good;
			img.src = "surfacedsub.png";
		}
		else {
			text.innerHTML = story9Bad;
			img.src = "broken-key.jpg";
		}
		choiceOne.innerHTML = choice3[0];
		choiceTwo.innerHTML = choice3[0];
		choiceThree.innerHTML = choice3[0];
		storyNumber = 17;
	}
}
function nextchoice2() {
	var text = document.getElementById('story');
	var choiceOne = document.getElementById('choice1');
	var choiceTwo = document.getElementById('choice2');
	var choiceThree = document.getElementById('choice3');
	var Number = document.getElementById('Number');
	var img = document.getElementById('pic')
	Number.innerHTML = storyNumber;
	if (storyNumber === 0) {
		text.innerHTML = storyStartChoiceExplore + " " + story1;
		choiceOne.innerHTML = choice1[1];
		choiceTwo.innerHTML = choice2[1];
		choiceThree.innerHTML = choice3[0];
		img.src = "CaptureFinished.jpg";
		storyNumber = 1;
	}
	else if (storyNumber === 1) {
		text.innerHTML = story1ChoiceDebris + " " + story2;
		choiceOne.innerHTML = choice1[2];
		choiceTwo.innerHTML = choice2[2];
		choiceThree.innerHTML = choice3[0];
		img.src = "Items.jpg";
		storyNumber = 2;
	}
	else if (storyNumber === 2) {
		text.innerHTML = story2ChoiceThermite + " " + story3;
		choiceOne.innerHTML = choice1[3];
		choiceTwo.innerHTML = choice2[3];
		choiceThree.innerHTML = choice3[1];
		img.src = "2doorsFinished.jpg";
		storyNumber = 3;
	}
	else if (storyNumber === 3) {
		text.innerHTML = story3Left;
		choiceOne.innerHTML = choice1[4];
		choiceTwo.innerHTML = choice2[4];
		choiceThree.innerHTML = choice3[0];
		img.src = "LeftDoor.png";
		storyNumber = 5;
	}
	else if (storyNumber === 4) {
		text.innerHTML = story3Left;
		choiceOne.innerHTML = choice1[4];
		choiceTwo.innerHTML = choice2[4];
		choiceThree.innerHTML = choice3[0];
		img.src = "LeftDoor.png";
		storyNumber = 5;
	}
	else if (storyNumber === 5) {
		text.innerHTML = story3LeftChoiceClimb + " " + story4Left;
		choiceOne.innerHTML = choice1[5];
		choiceTwo.innerHTML = choice2[5];
		choiceThree.innerHTML = choice3[0];
		backpack[1] = "Glowsticks";
		img.src = "goldbox.jpg";
		storyNumber = 7;
	}
	else if (storyNumber === 7) {
		text.innerHTML = story4LeftChoiceNo + " " + story5Right;
		choiceOne.innerHTML = choice3[0];
		img.src = "darkness.jpg";
		if (backpack[0] === "Thermite") {
			choiceThree.innerHTML = choice3[2];
			choiceTwo.innerHTML = choice2[6];
		}
		choiceTwo.innerHTML = choice2[6];
		storyNumber = 8;
	}
	else if (storyNumber === 8) {
		text.innerHTML = story6RightChoiceGlowstick + " " + story7Right;
		choiceThree.innerHTML = choice1[6];
		choiceTwo.innerHTML = choice2[7];
		choiceOne.innerHTML = choice3[0];
		img.src = "glowstick.jpg";
		storyNumber = 9;
	}
	else if (storyNumber === 8.5) {
		text.innerHTML = story7Right;
		choiceThree.innerHTML = choice1[6];
		choiceTwo.innerHTML = choice2[7];
		choiceOne.innerHTML = choice3[0];
		img.src = "rightdoor.jpg";
		storyNumber = 9;
	}
	else if (storyNumber === 9) {
		if (backpack[2] === "Gold key") {
			text.innerHTML = "You don't need to go back up there.";
			if (backpack[3] === "Rusty key") {
				storyNumber = 15;
				end();
			}
			else {
				storyNumber = 9;
			}
		}
		else {
			text.innerHTML = story8RightUp;
			choiceOne.innerHTML = choice3[0];
			choiceTwo.innerHTML = choice2[9];
			choiceThree.innerHTML = choice1[8];
			img.src = "topdoor.jpg";
			storyNumber = 8.5;
		}
	}
	else if (storyNumber === 10) {
		text.innerHTML = story7RightLeftBubble;
		choiceOne.innerHTML = choice3[0];
		choiceTwo.innerHTML = choice2[7];
		choiceThree.innerHTML = choice1[6];
		backpack[3] = "Rusty key";
		backpack[4] = "Blue key";
		img.src = "bluekey.jpg";
		storyNumber = 9;
	}
	else if (storyNumber === 16) {
		if (randomNumber > 75) {
			text.innerHTML = story9Good;
			img.src = "surfacedsub.png";
		}
		else {
			text.innerHTML = story9Bad;
			img.src = "broken-key-blue.jpg";
		}
		choiceOne.innerHTML = choice3[0];
		choiceTwo.innerHTML = choice3[0];
		choiceThree.innerHTML = choice3[0];
		storyNumber = 17;
	}
}
function nextchoice3() {
	var text = document.getElementById('story');
	var choiceOne = document.getElementById('choice1');
	var choiceTwo = document.getElementById('choice2');
	var choiceThree = document.getElementById('choice3');
	var Number = document.getElementById('Number');
	var img = document.getElementById('pic')
	Number.innerHTML = storyNumber;
	if (storyNumber === 3 || storyNumber === 4) {
		text.innerHTML = story5Right;
		choiceOne.innerHTML = "Go back and go through the left door";
		img.src = "darkness.jpg";
		if (backpack[0] === "Thermite") {
			choiceThree.innerHTML = choice3[2];
			storyNumber = 8;
		}
		else {
			choiceThree.innerHTML = choice3[0];
			storyNumber = 4;
		}
		choiceTwo.innerHTML = choice3[0];
	}
	else if (storyNumber === 8) {
		text.innerHTML = story6RightChoiceThermite + " " + story7Right;
		choiceOne.innerHTML = choice3[0];
		choiceTwo.innerHTML = choice2[7];
		choiceThree.innerHTML = choice1[6];
		img.src = "thermite.jpg";
		storyNumber = 9;
	}
	else if (storyNumber === 8.5) {
		text.innerHTML = story8RightUpKey + " You go back to the previous room.";
		choiceOne.innerHTML = choice3[0];
		choiceTwo.innerHTML = choice2[7];
		choiceThree.innerHTML = choice1[6];
		backpack[2] = "Gold key";
		img.src = "golden-key.png";
		storyNumber = 9;
	}
	else if (storyNumber === 9) {
		if (backpack[3] === "Rusty key") {
			text.innerHTML = "You don't need to go back through there.";
			if (backpack[2] === "Gold key") {
				storyNumber = 15;
				end();
			}
			else {
				storyNumber = 9;
			}
		}
		else {
			text.innerHTML = story7RightLeft;
			choiceOne.innerHTML = choice3[0];
			choiceTwo.innerHTML = choice2[8];
			choiceThree.innerHTML = choice1[7];
			img.src = "rightleftdoor.jpg";
			storyNumber = 10;
		}
	}
	else if (storyNumber === 10) {
		backpack[3] = "Rusty key";
		text.innerHTML = story7RightLeftRust;
		choiceOne.innerHTML = choice3[0];
		choiceTwo.innerHTML = choice2[7];
		choiceThree.innerHTML = choice1[6];
		img.src = "rustykey.jpg";
		storyNumber = 9;
	}
	else if (storyNumber === 16) {
		if (randomNumber > 25) {
			text.innerHTML = story9Gold;
			img.src = "MantaRobot.jpg";
			img.style.visibility = 'visible';
		}
		else {
			text.innerHTML = story9Good;
			img.src = "surfacedsub.png";
		}
		choiceOne.innerHTML = choice3[0];
		choiceTwo.innerHTML = choice3[0];
		choiceThree.innerHTML = choice3[0];
		storyNumber = 17;
	}
}
function end() {
	var text = document.getElementById('story');
	var choiceOne = document.getElementById('choice1');
	var choiceTwo = document.getElementById('choice2');
	var choiceThree = document.getElementById('choice3');
	var Number = document.getElementById('Number');
	var img = document.getElementById('pic')
	Number.innerHTML = storyNumber;
	text.innerHTML += " " + story9;
	img.src = "hatch.jpg";
	storyNumber = 16;
	if (backpack[4] === "Blue key") {
		choiceOne.innerHTML = choice1[9];
		choiceTwo.innerHTML = choice2[10];
		storyNumber = 16;
	}
	if (backpack[4] != "Blue key") {
		choiceTwo.innerHTML = choice3[0];
	}
	if (backpack[2] === "Gold key") {
		choiceThree.innerHTML = choice3[3];
		storyNumber = 16;
	}
	if (backpack[3] === "Rusty key") {
		choiceOne.innerHTML = choice1[9];
		storyNumber = 16;
	}
}