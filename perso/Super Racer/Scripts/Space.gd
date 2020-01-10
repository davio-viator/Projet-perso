extends Node2D

const Roche = preload("res://Assets/Roche.tscn")
const bonus = preload("res://Assets/SpeedBonus.tscn")

var score = 0

func _ready():
	pass



func _process(delta):
	$Vaisseau.score  = score
	score = $Vaisseau.score
	scoring()
	ammo_display()
	speed_bonus_display()



func ammo_display() :
	var ammo  = $Vaisseau.current_ammo
	if score >= 4000:
		if ammo == 3 :
			$Control/Label3.text = "Ammo : ---"
		if ammo == 2 :
			$Control/Label3.text = "Ammo : --"
		if ammo == 1 :
			$Control/Label3.text = "Ammo : -"
		if ammo == 0 :
			$Control/Label3.text = "Ammo : reload"


func speed_bonus_display():
	if $Vaisseau.speed_boost == true :
		$Control/Label.text = "Bonus de deplacement"
	elif $Vaisseau.speed_boost == false :
		$Control/Label.text = ""
	


func spawn_roche():
	var roche = Roche.instance()
	if score > 3000 :
		roche.speed = -700
	if score > 5000 :
		roche.speed = -900
	var rand = Vector2(1280,rand_range(20,700))
	roche.set_position(rand)
	add_child(roche)


func spawn_bonus():
	var Bonus = bonus.instance()
	var random = rand_range(0,100)
	if score <4000 :
		if random < 10 :
			var rand = Vector2(1280,rand_range(20,700))
			Bonus.set_position(rand)
			add_child(Bonus)	
	else :
		if random < 3 :
			var rand = Vector2(1280,rand_range(20,700))
			Bonus.set_position(rand)
			add_child(Bonus)	



func _on_Timer_timeout():
	$Timer.wait_time *=0.992
	if $Timer.wait_time <= 0.1:
		$Timer.wait_time = 0.1
	if $"Score final".visible == false:
		spawn_roche()
		spawn_bonus()
	
func scoring():
	#score = $Vaisseau.score
	if score >5000 : 
		$Vaisseau.speed = 500
		score+=1
	if score > 10000 :
		$Vaisseau.speed = 700
		score +=1
	$Control/Score.text = str(score)
	score+=1
		


func _on_gauche_pressed():
	print("gauche")
	$Vaisseau.velocity.y-=1
	$Vaisseau.move_and_slide($Vaisseau.velocity)


func _on_droite_pressed():
	print("droite")
	$Vaisseau.velocity.y+=1
	$Vaisseau.move_and_slide($Vaisseau.velocity)
