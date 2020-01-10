extends KinematicBody2D

export var speed = -500
onready var end  = preload("res://Assets/Score final.tscn")
var velocity = Vector2()

func _ready():
	speed = rand_range(speed-100,speed+100)

func _physics_process(delta):
	#if space.score > 3000:
		#speed = -700
	velocity.x = speed
	velocity = move_and_slide(velocity) * delta
	if global_position.x <-20 :
		get_tree().queue_delete(self)
		
	

func _on_Area2D_body_entered(body):
	if body.name == "Vaisseau" :
		var final_scene = body.get_parent().get_node("Score final")
		final_scene.get_node("score").text = str(body.score)
		final_scene.visible = true
		#get_tree().reload_current_scene()
		#pass