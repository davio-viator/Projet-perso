extends KinematicBody2D

export var speed = 350
var velocity = Vector2()
const laser = preload("res://Assets/Laser.tscn")

export var fire_rate = 1.5
export var clip_size = 3
export var reload_rate = 1
var speed_boost = false
var score = 0

var current_ammo = clip_size
var can_fire = true
var reloading = false


func _ready():
	pass


func get_input():
	velocity = Vector2()
	if Input.is_action_pressed("monter"):
		velocity.y-=1
	if Input.is_action_pressed("dessendre"):
		velocity.y+=1
		
		
	if speed_boost == false :
		velocity = velocity.normalized() * speed
	elif speed_boost == true :
		velocity = velocity.normalized() * (speed + 150)
		yield(get_tree().create_timer(3),"timeout")
		speed_boost = false
		
		
	if Input.is_action_pressed("tirer") and can_fire and score >= 4000:
		if current_ammo > 0 and not reloading :
			print("fired")
			can_fire = false
			current_ammo -= 1
			shoot()
			yield(get_tree().create_timer(fire_rate),"timeout")
			
			can_fire = true
			
		elif not reloading :
			print("reloading")
			reloading = true
			yield(get_tree().create_timer(reload_rate),"timeout")
			current_ammo = clip_size
			reloading = false
			print("reload complete")
	
		
		
		
func _physics_process(delta):
	get_input()
	velocity = move_and_slide(velocity)
	
	
func shoot():
	var Laser1 = laser.instance()
	var Laser2 = laser.instance()
	Laser1.start($Position2D.global_transform)
	Laser2.start($Position2D2.global_transform)
	get_parent().add_child(Laser1)
	get_parent().add_child(Laser2)


