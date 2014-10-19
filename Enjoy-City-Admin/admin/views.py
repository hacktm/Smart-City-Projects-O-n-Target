from django.http import HttpResponse
from django.shortcuts import render, render_to_response, get_object_or_404
from django.template.context import RequestContext
from django.contrib.auth.decorators import login_required
from django.contrib import messages

from .models import Location, Content
from .forms import LocationForm


@login_required
def home(request):
    locations = Location.objects.filter(user=request.user.id)
    return render_to_response('admin/home.html', {
                'locations' : locations
                }, context_instance=RequestContext(request))

@login_required
def show_location_details(request, location_id):
    try:
        location = Location.objects.get(id=location_id)
        location_form = LocationForm(instance=location)
        content_items = Content.objects.filter(location_id=location_id)
    except Location.DoesNotExist:
        # TODO: render a nice 404 page.
        return HttpResponse('Not Found', status=404)

    return render_to_response('admin/location.html', {
                'location_form': location_form,
                'content_items': content_items
                }, context_instance=RequestContext(request))

@login_required
def add_location(request):
    if request.method == 'POST':
        print request.POST
        location_form = LocationForm(request.POST, user=request.user.id)
        if location_form.is_valid():
            location_form.save()
            messages.success(request, 'The location was added successfully!')
        else:
            print location_form.errors
            messages.error(request, 'An error occured!')
    elif request.method == 'GET':
        location_form = LocationForm()

    return render_to_response('admin/location.html', {
                'location_form': location_form
                }, context_instance=RequestContext(request))
