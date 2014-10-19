from django.db import models

# Create your models here.
from djangotoolbox.fields import ListField, EmbeddedModelField
from django_mongodb_engine.storage import GridFSStorage

gridfs_storage = GridFSStorage()

class Cordinates(models.Model):
    lat = models.FloatField(null=False, blank=False)
    lng = models.FloatField(null=False, blank=False)

    class Meta:
        verbose_name_plural = 'Coordinates'


class City(models.Model):
    name = models.CharField(max_length=255)
    #coordinates = EmbeddedModelField('Coordinates')

    class Meta:
        verbose_name_plural = 'Cities'

class Tag(models.Model):
    value = models.CharField(max_length=255)

    class Meta:
        verbose_name_plural = 'Tags'

class Category(models.Model):
    name = models.CharField(max_length=255)
    photo = models.FileField(storage=gridfs_storage, upload_to='/media/categories/')

    class Meta:
        verbose_name_plural = 'Categories'

class Location(models.Model):
    user = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    description = models.TextField()
    category = models.IntegerField(null=True, blank=True)
    tags = ListField(models.CharField(max_length=15), null=True, blank=True)
    #coordinates = EmbeddedModelField('Coordinates')
    email = models.EmailField()
    phone_number = models.CharField(max_length=15, null=True, blank=True)
    rating = models.PositiveSmallIntegerField(blank=True, null=True)
    photo = models.FileField(storage=gridfs_storage, upload_to='/media/locations/',
                            null=True, blank=True)

    class Meta:
        verbose_name_plural = 'Locations'

class Content(models.Model):
    location_id = models.CharField(max_length=15)
    description = models.TextField(null=True, blank=True)
    tags = ListField(models.PositiveIntegerField(), null=True, blank=True)
    photo = models.FileField(storage=gridfs_storage, upload_to='/media/locations/',
                            blank=True, null=True)
    expiration_date = models.DateTimeField(null=True, blank=True)

    class Meta:
        verbose_name_plural = 'Content Items'
