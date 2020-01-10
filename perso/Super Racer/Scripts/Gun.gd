extends Node2D

class_name Gun


const laser = preload("res://Assets/Laser.tscn")

export var fire_rate = 1.5
export var clip_size = 3
export var reload_rate = 1.5

onready var raycast = $"../Sprite/Raycast"
var current_ammo = clip_size
var can_fire = true
var reloading = false

func _ready():
	pass


func shoot():
	var velocity = get_parent().velocity
	print(velocity)
	var Laser = laser.instance()
	Laser.set_position(velocity)
	add_child(Laser)

func check_collision():
	print("test")
	if raycast.is_colliding():
		("test 1 ")
		var collider = raycast.get_collider()
		if collider.is_in_group("Ennemy"):
			print("test 2")
			collider.queue_free()
			print("Killed " + collider.name)
		
		
		
		
		
		
		
		
		
		