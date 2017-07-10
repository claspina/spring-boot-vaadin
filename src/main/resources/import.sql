INSERT INTO public.users (username, password, enabled) VALUES ('admin', '$2a$10$6TajU85/gVrGUm5fv5Z8beVF37rlENohyLk3BEpZJFi6Av9JNkw9O', true);
INSERT INTO public.users (username, password, enabled) VALUES ('user', '$2a$10$5uKS72xK2ArGDgb2CwjYnOzQcOmB7CPxK6fz2MGcDBM9vJ4rUql36', true);

INSERT INTO public.authorities (username, authority) VALUES ('admin', 'ADMINISTRADOR');
INSERT INTO public.authorities (username, authority) VALUES ('admin', 'BASICO');
INSERT INTO public.authorities (username, authority) VALUES ('user', 'BASICO');