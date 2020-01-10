extends Area2D

export var speed = 1000
var velocity = Vector2()

func _ready():
	pass
	
func start(transformx) :
	transform = transformx
	velocity = transform.x * speed

	
func _physics_process(delta):
	transform.origin += velocity * delta
	
	

func _on_Laser_area_entered(area):
	if area.name == "RocherArea" :
		area.get_parent().queue_free()
		print(get_parent().name)
		queue_free()
