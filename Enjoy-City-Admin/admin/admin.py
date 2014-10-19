from django.contrib import admin

from .models import City, Tag, Category, Location, Content

admin.site.register(City)
admin.site.register(Tag)
admin.site.register(Category)
admin.site.register(Content)
admin.site.register(Location)
