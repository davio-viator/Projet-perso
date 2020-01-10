extends KinematicBody2D

export var speed = -500
var velocity = Vector2()

func _ready():
	pass

func _physics_process(delta):
	velocity.x = speed
	velocity = move_and_slide(velocity) * delta
	if global_position.x <-20 :
		get_tree().queue_delete(self)

func _on_BonusArea_body_entered(body):
	if body.name == "Vaisseau":
		#body.speed_boost = true
		#body.score += 100
		#yield(get_tree().create_timer(0.2),"timeout")
		#queue_free()
		print()


func _on_BonusArea_body_exited(body):
	if body.name == "Vaisseau":
		#body.speed_boost = true
		#body.score += 100
		#yield(get_tree().create_timer(0.2),"timeout")
		#queue_free()
		print()


func _on_BonusArea_area_entered(area):
	if area.name == "ShipArea":
		area.get_parent().speed_boost = true
		area.get_parent().score += 100
		queue_free()
