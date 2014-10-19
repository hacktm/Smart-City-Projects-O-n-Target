from django.conf.urls import patterns, url

urlpatterns = patterns('admin.views',
    url(r'^$', 'home', name='home'),
    url(r'^location/(?P<location_id>[0-9a-zA-Z]+)/$', 'show_location_details', name='location'),
    url(r'^add-location/$', 'add_location', name='add-location'),
)
