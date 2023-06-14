insert into news (id, title, create_date_news, update_date_news, text)
values (1, 'COVID-19 cases surge in major cities', '2022-03-10 16:11:56', '2022-03-10 16:11:56',
        'As the Delta variant of COVID-19 continues to spread, major cities around the world are seeing a surge in cases. Hospitals are becoming overwhelmed and governments are implementing stricter measures to try and curb the spread of the virus.'),
       (2, 'Record-breaking heatwave hits the West Coast', '2022-06-24 18:06:20', '2022-06-24 18:06:20',
        'Temperatures have soared to unprecedented levels in parts of the West Coast, with some areas experiencing highs of over 120 degrees Fahrenheit. The extreme heat has caused power outages, wildfires, and health concerns for residents.'),
       (3, 'World leaders gather for climate summit', '2023-01-13 04:17:18', '2023-01-13 04:17:18',
        'Leaders from around the world have come together for a climate summit aimed at addressing the urgent need to reduce greenhouse gas emissions and combat climate change. The summit is seen as a crucial step in the global effort to create a sustainable future.'),
       (4, 'New study reveals alarming levels of plastic pollution in oceans', '2022-01-13 15:09:26', '2022-01-13 15:09:26',
        'A new study has found that plastic pollution in the world''s oceans is even worse than previously thought, with microplastics now present in every corner of the ocean. The findings have sparked renewed calls for action to address the plastic waste crisis.'),
       (5, 'Celebrity couple announces surprise engagement', '2023-05-20 00:36:00', '2023-05-20 00:36:00',
        'In a move that has shocked fans and media alike, a celebrity couple has announced their engagement after a whirlwind romance. The news has sparked speculation about their future plans and wedding details.'),
       (6, 'Major tech company announces groundbreaking new product', '2022-10-21 17:18:41', '2022-10-21 17:18:41',
        'A major tech company has unveiled a new product that promises to revolutionize the industry. The product boasts cutting-edge features and has already generated significant buzz among consumers and investors.'),
       (7, 'Massive wildfire threatens residential areas', '2022-11-18 02:40:05', '2022-11-18 02:40:05',
        'A massive wildfire has broken out in a rural area, threatening nearby residential communities. Firefighters are working around the clock to contain the blaze, but high winds and dry conditions are making their job difficult.'),
       (8, 'Local community comes together to support small businesses', '2022-02-18 11:58:40', '2022-02-18 11:58:40',
        'In the wake of the pandemic, a local community has rallied together to support small businesses struggling to stay afloat. Residents have organized events and initiatives to promote local commerce and help businesses survive during these challenging times.'),
       (9, 'Controversial politician faces impeachment proceedings', '2022-04-21 22:26:53', '2022-04-21 22:26:53',
        'A controversial politician is facing impeachment proceedings over allegations of corruption and abuse of power. The proceedings have sparked heated debate among the public and political commentators.'),
       (10, 'Scientists discover potential cure for rare disease', '2022-06-30 22:17:20', '2022-06-30 22:17:20',
        'In a breakthrough that could change the lives of millions, scientists have discovered a potential cure for a rare disease that has long been considered incurable. The news has given hope to patients and their families around the world.');

insert into news (id, title, update_date_news, create_date_news, text)
values (11, 'Major airline announces expansion plans', '2022-10-12 11:50:30', '2022-10-12 11:50:30',
        'A major airline has announced plans to expand its routes and increase its fleet, in response to growing demand for air travel. The move is expected to create jobs and boost the economy in the regions it serves.'),
       (12, 'New study links social media use to mental health issues', '2022-07-20 01:44:01', '2022-07-20 01:44:01',
        'A new study has found a correlation between heavy social media use and increased rates of depression and anxiety. The findings have raised concerns about the impact of technology on mental health, particularly among young people.'),
       (13, 'Global food prices reach record highs', '2022-06-09 05:46:04', '22022-06-09 05:46:04',
        'Food prices around the world have reached record highs, driven by factors such as climate change, supply chain disruptions, and increased demand. The situation is causing hardship for many families and raising concerns about food security.'),
       (14, 'Renowned artist unveils new exhibition', '2023-01-06 02:16:12', '2023-01-06 02:16:12',
        'A renowned artist has unveiled a new exhibition featuring their latest works. The exhibition has been highly anticipated by fans and critics alike, and is expected to draw large crowds.'),
       (15, 'Local charity raises funds for disaster relief efforts', '2023-03-25 04:37:46', '2023-03-25 04:37:46',
        'A local charity has launched a fundraising campaign to support disaster relief efforts in a region affected by a recent natural disaster. The campaign has received widespread support from the community and beyond.'),
       (16, 'New law aims to reduce carbon emissions', '2022-03-16 01:02:11', '2022-03-16 01:02:11',
        'A new law has been passed aimed at reducing carbon emissions and promoting renewable energy sources. The law is seen as a significant step in the fight against climate change.'),
       (17, 'Medical breakthrough offers hope for cancer patients', '2023-01-16 14:28:05', '2023-01-16 14:28:05',
        'A medical breakthrough has been made in the treatment of a particular type of cancer, offering hope to patients who previously had few options. The breakthrough has been hailed as a major achievement in the field of oncology.'),
       (18, 'Local sports team wins championship', '2022-08-25 11:33:28', '2022-08-25 11:33:28',
        'A local sports team has won a championship in their respective league, bringing joy and pride to their fans and community. The victory is seen as a testament to the teams hard work and dedication.'),
       (19, 'New technology promises to revolutionize transportation', '2023-02-22 16:55:35', '2023-02-22 16:55:35',
        'A new technology has been developed that promises to revolutionize transportation, making it faster, safer, and more efficient. The technology is being hailed as a game-changer in the industry.'),
       (20, 'Global leaders pledge to tackle poverty and inequality', '2022-01-20 08:53:19', '2022-01-20 08:53:19',
        'At a recent summit, global leaders pledged to work together to tackle poverty and inequality around the world. The pledges include measures such as increased aid to developing countries and efforts to promote economic growth and job creation.');

insert into comments(news_id, id, username, create_date_comment, update_date_comment, text)
values (1,1,'Dagmar','2022-04-30 17:59:39','2023-05-11 14:05:51',	 'Stunning work, you have a real eye for detail'),
       (1,2,'Sam','2023-02-27 11:29:55','2023-05-13 03:07:54',	 'You truly have a gift for this, keep it up!'),
       (1,3,'Sarette','2022-09-05 21:07:32','2023-05-20 04:55:40',	 'Your creativity is unmatched, truly impressive'),
       (1,4,'Cissiee','2022-06-25 00:23:57','2023-05-04 13:51:43',	 'I am blown away by your attention to detail'),
       (1,5,'Madelle','2022-09-08 18:59:14','2023-05-22 07:10:02',	 'You consistently exceed expectations, amazing job'),
       (1,6,'Carmela','2022-09-02 00:16:39','2023-05-16 17:21:11',	 'Your work is consistently outstanding, keep it up'),
       (1,7,'Fayre','2022-08-06 20:56:29','2023-05-26 12:45:58',	 'Your unique perspective shines through in your work, truly inspiring'),
       (1,8,'Hannis','2022-12-03 17:42:05','2023-05-20 15:28:05',	 'Your dedication to your craft is admirable, well done'),
       (1,9,'Corry','2022-02-12 16:54:41','2023-05-05 06:47:25',	 'Your professionalism is always apparent in your work, great job'),
       (2,10,'Correy','2022-08-17 17:59:19','2023-05-19 22:20:39',	 'Your passion for what you do is evident in every piece of work, keep it up'),
       (2,11,'Valeda','2023-02-24 05:30:58','2023-05-31 11:34:23',	 'You have an incredible eye for design, impressive work'),
       (2,12,'Ethel','2022-11-20 00:05:15','2023-05-29 19:34:25',	 'Your work is fresh and innovative, always ahead of the curve'),
       (2,13,'Nickie','2022-10-01 23:02:21','2023-05-13 10:02:32',	 'You are a true artist, your creativity knows no bounds'),
       (2,14,'Belva','2022-02-06 19:23:54','2023-05-18 14:21:58',	 'You have an amazing ability to bring ideas to life, truly inspiring'),
       (2,15,'Sallie','2022-06-01 20:01:48','2023-05-05 05:14:54',	 'Your work combines form and function seamlessly, beautiful and functional'),
       (2,16,'Madeleine','2022-04-27 22:32:39','2023-05-02 15:36:51',	 'You are a masterful expert in your craft, impressive work'),
       (2,17,'Jordan','2022-07-03 12:17:58','2023-05-20 23:03:43',	 'Your work has a timeless quality that will never go out of style, well done'),
       (2,18,'Aimil','2022-10-09 19:44:46','2023-05-14 14:49:13',	 'You have a gift for storytelling through your work, truly impressive'),
       (2,19,'Melanie','2022-12-17 17:05:07','2023-05-09 22:08:45',	 'Your work is always on point and on trend, great job'),
       (2,20,'Brana','2022-02-04 20:33:26','2023-05-27 09:08:02',	 'Your use of color and composition is breathtaking, flair for color and composition'),
       (3,21,'Annaliese','2022-08-10 03:35:52','2023-05-19 15:19:48',	 'Your work exudes elegance and sophistication, well done'),
       (3,22,'Chloris','2022-01-31 05:43:44','2023-05-10 11:15:20',	 'You have an intuitive understanding of what works best in your field, impressive work'),
       (3,23,'Bernie','2023-04-19 03:34:03','2023-05-08 19:18:13',	 'Your work always has a deeper meaning and intention behind it, sense of purpose'),
       (3,24,'Tonia','2022-05-14 23:49:12','2023-05-18 00:19:50',	 'Your sense of style is undeniably impressive, great job'),
       (3,25,'Donetta','2022-08-10 07:56:21','2023-05-31 18:11:21',	 'Your work always feels fresh and exciting, never repetitive or dull'),
       (3,26,'Selma','2022-05-25 10:27:26','2023-05-16 04:17:16',	 'Your work is visually stunning, truly impressive'),
       (3,27,'Sharlene','2022-10-01 23:57:50','2023-05-30 03:29:20',	 'Your limitless creativity is truly inspiring, keep it up'),
       (3,28,'Dennie','2023-04-13 09:46:38','2023-05-17 10:57:05',	 'Your work is always polished and professional, well done'),
       (3,29,'Caressa','2022-03-23 11:58:47','2023-05-22 00:23:11',	 'You have a knack for elevating everyday objects and experiences, making the mundane beautiful'),
       (3,30,'Judy','2022-01-07 02:53:38','2023-05-20 19:39:58',	 'Your work is both bold and understated, impressive job'),
       (4,31,'Deane','2022-07-26 02:49:42','2023-05-20 23:18:48',	 'You have a natural ability to connect with people through your work, great job'),
       (4,32,'Brynna','2023-04-30 18:31:31','2023-05-26 15:20:27',	 'Your work is thought-provoking and stimulating, impressive job'),
       (4,33,'Rosaline','2023-04-26 16:38:17','2023-05-16 00:07:55',	 'Your work has a sense of balance and harmony that is exceptional, well done'),
       (4,34,'Babita','2022-10-18 19:59:04','2023-05-26 23:00:41',	 'Your work has an enduring appeal that transcends trends, timeless quality'),
       (4,35,'Rozele','2022-01-09 10:01:07','2023-05-26 19:11:50',	 'You have a talent for finding beauty in the mundane, making the ordinary extraordinary'),
       (4,36,'Darlleen','2022-06-03 09:42:35','2023-05-03 15:07:44',	 'Your work is both powerful and delicate, impressive job'),
       (4,37,'Cyb','2022-07-24 06:29:09','2023-05-02 03:04:08',	 'Your work has a profound emotional impact on viewers, evoke emotion'),
       (4,38,'Selia','2022-03-20 17:19:08','2023-05-09 17:47:30',	 'Your work is surprising and unexpected, truly impressive'),
       (4,39,'Dorice','2023-01-18 22:49:04','2023-05-21 21:11:23',	 'Your work leaves a lasting impression on those who experience it, creating memorable experiences'),
       (4,40,'Jaclyn','2022-02-08 19:27:15','2023-05-17 11:04:19',	 'Your work is always fresh and exciting, never repetitive or dull'),
       (5,41,'Lila','2022-03-25 06:59:11','2023-05-06 21:24:26',	 'Your work is both intricate and easy to understand, simplicity in complexity'),
       (5,42,'Lanna','2023-01-15 04:56:43','2023-05-17 23:16:37',	 'Your work is both subtle yet bold, impressive job'),
       (5,43,'Yetty','2022-02-22 14:08:43','2023-05-07 18:10:00',	 'Your work has a delightful sense of wit and humor, great job'),
       (5,44,'Elise','2022-12-10 01:57:48','2023-05-28 07:57:03',	 'Your work exudes vitality and liveliness, full of energy'),
       (5,45,'Clary','2022-02-03 10:14:39','2023-05-29 16:17:42',	 'You have a gift for capturing the essence of your subject matter, well done'),
       (5,46,'Chloris','2022-04-28 13:28:32','2023-05-30 05:10:31',	 'Your work inspires people to dream big, truly inspiring'),
       (5,47,'Tabbatha','2022-11-29 13:49:18','2023-05-23 06:09:37',	 'Your work creates a sense of wonder that is truly captivating, sense of wonder'),
       (5,48,'Dianemarie','2022-03-03 01:23:49','2023-05-07 10:41:26',	 'Your intense passion for what you do shines through in every piece of work, full of passion'),
       (5,49,'Mignon','2023-01-05 00:57:56','2023-05-11 01:54:28',	 'Your work defies expectations and pushes boundaries, making the impossible possible'),
       (5,50,'Mellicent','2023-04-12 20:11:07','2023-05-02 08:52:04',	 'Your work is both classic and modern at the same time, timeless and contemporary'),
       (6,51,'Orelia','2022-05-01 17:11:23','2023-05-06 19:42:47',	 'Your work has a rich depth and complexity that is truly impressive, depth and complexity'),
       (6,52,'Trixi','2023-02-14 03:24:12','2023-05-06 20:14:59',	 'Your work is always surprising and unexpected, great job'),
       (6,53,'Goldie','2022-02-14 12:10:59','2023-05-09 08:38:59',	 'Your work creates a sense of mystery and intrigue that is truly fascinating, sense of mystery and intrigue'),
       (6,54,'Chastity','2022-11-09 04:16:50','2023-05-09 02:29:58',	 'Your work is both enigmatic and accessible at the same time, mysterious yet transparent'),
       (6,55,'Pamella','2022-02-08 04:07:47','2023-05-17 03:18:07',	 'Your work has a magical quality that enchants and delights, sense of magic'),
       (6,56,'Livvyy','2022-01-28 15:04:51','2023-05-03 05:52:06',	 'Your work is bursting with energy and vitality, full of life'),
       (6,57,'Miquela','2022-03-23 16:36:17','2023-05-15 03:13:41',	 'You have a talent for turning the ordinary into something extraordinary, making the mundane magical'),
       (6,58,'Consuela','2022-03-19 11:57:24','2023-05-09 17:41:49',	 'Your work is uniquely your own, authentic and original'),
       (6,59,'Althea','2022-02-12 04:54:49','2023-05-20 12:51:20',	 'Your work has a natural, effortless flow to it, sense of rhythm and flow'),
       (6,60,'Brianna','2022-04-24 19:31:11','2023-05-26 21:24:10',	 'Your work is infused with emotion and heart, full of heart'),
       (7,61,'Tera','2022-08-12 08:02:29','2023-05-24 06:43:20',	 'Your work creates a sense of wonder and mystery that is truly captivating, sense of wonder and mystery'),
       (7,62,'Shaine','2022-08-29 14:46:37','2023-05-30 04:46:24',	 'Your work strikes the perfect balance between refinement and fun, elegant and whimsical'),
       (7,63,'Trixi','2023-04-02 09:45:37','2023-05-25 06:07:02',	 'Your work exudes grace and beauty, sense of beauty and grace'),
       (7,64,'Robbi','2022-07-28 22:38:44','2023-05-03 01:47:19',	 'Your work always keeps people on their toes with its surprises, full of surprises'),
       (7,65,'Ulrike','2022-12-08 12:13:07','2023-05-25 17:07:04',	 'You have a talent for finding beauty in unexpected places, making the ordinary extraordinary'),
       (7,66,'Starla','2023-03-07 19:02:43','2023-05-16 21:54:38',	 'Your work strikes the perfect balance between sophistication and playfulness, sophisticated and playful'),
       (7,67,'Shauna','2022-05-25 07:41:57','2023-05-27 08:21:19',	 'Your work has a perfect sense of harmony and balance, sense of harmony and balance'),
       (7,68,'Arabel','2023-02-06 21:30:55','2023-05-06 22:58:38',	 'Your work exudes hope and positivity, full of optimism'),
       (7,69,'Bobbi','2022-03-21 06:26:35','2023-05-07 08:36:39',	 'Your work radiates kindness and compassion, full of warmth'),
       (7,70,'Bettine','2023-01-20 19:21:45','2023-05-30 13:08:35',	 'Your work is both strong and delicate, powerful and graceful'),
       (8,71,'Merrie','2022-02-01 15:52:03','2023-05-21 03:04:25',	 'Your work has a calming, soothing effect on viewers, sense of peace and calm'),
       (8,72,'Celisse','2022-06-02 02:36:10','2023-05-07 23:57:02',	 'Your work always distills complicated ideas into simple, digestible concepts, making the complex simple'),
       (8,73,'Flo','2022-10-29 14:24:08','2023-05-02 21:04:07',	 'Your work has a fine, delicate quality to it, intricate and delicate'),
       (8,74,'Marika','2022-09-19 00:09:16','2023-05-05 17:45:09',	 'Your work has a subtle complexity that rewards closer inspection, depth and nuance'),
       (8,75,'Winifred','2022-07-09 05:15:26','2023-05-30 10:06:17',	 'Your work always keeps people guessing, surprising and unexpected'),
       (8,76,'Thalia','2023-01-05 00:06:17','2023-05-03 01:11:11',	 'Your work creates a sense of mystery and fascination in viewers, sense of mystery and intrigue'),
       (8,77,'Gretal','2022-11-20 13:42:03','2023-05-15 18:17:57',	 'Your work has a magical, otherworldly quality to it, full of magic and enchantment'),
       (8,78,'Tina','2022-07-13 14:30:30','2023-05-25 15:44:40',	 'Your work defies expectations and pushes boundaries, making the impossible possible'),
       (8,79,'Sashenka','2022-12-27 00:28:29','2023-05-31 18:35:34',	 'Your work is hypnotic and captivating, captivating and mesmerizing'),
       (8,80,'Orsola','2023-01-21 17:33:41','2023-05-21 12:50:42',	 'Your work sparks curiosity and wonder in viewers, sense of wonder and curiosity'),
       (9,81,'Cordi','2022-04-29 10:36:09','2023-05-14 10:58:14',	 'Your work takes viewers on an exciting journey, full of adventure'),
       (9,82,'Ricky','2022-06-26 15:07:02','2023-05-16 06:24:20',	 'Your work creates a sense of adventure and exploration in viewers, sense of adventure and exploration'),
       (9,83,'Fanchon','2022-11-23 22:37:46','2023-05-25 22:34:50',	 'Your work is both bold and adventurous, daring and adventurous'),
       (9,84,'Layla','2022-01-31 13:08:37','2023-05-05 06:24:16',	 'Your work inspires a sense of discovery and curiosity in viewers, sense of exploration and discovery'),
       (9,85,'Jaclyn','2023-02-05 23:43:52','2023-05-22 15:56:22',	 'Your work always keeps people guessing, surprising and unexpected'),
       (9,86,'Christal','2022-03-03 01:45:32','2023-05-29 02:32:45',	 'You have a talent for reinventing the familiar in new and exciting ways, making the familiar new'),
       (9,87,'Marinna','2022-11-01 01:52:23','2023-05-29 01:50:47',	 'Your work is at the forefront of innovation and progress, innovative and groundbreaking'),
       (9,88,'Florencia','2022-11-04 16:15:59','2023-05-17 13:24:26',	 'Your boundless creativity is truly impressive, innovation and creativity'),
       (9,89,'Arabel','2022-02-20 04:27:30','2023-06-01 01:10:37',	 'Your work is always fresh and full of new ideas, full of new ideas'),
       (9,90,'Sam','2022-03-18 00:46:10','2023-05-05 16:05:17',	 'Your work embodies the spirit of progress and innovation, sense of innovation and progress'),
       (10,91,'Madeleine','2022-06-27 13:53:05','2023-05-16 14:14:18',	 'Your work is both forward-thinking and visionary, forward-thinking and visionary'),
       (10,92,'Joeann','2022-08-08 02:33:50','2023-05-30 08:33:05',	 'Your work inspires a sense of progress and evolution in viewers, sense of progress and evolution'),
       (10,93,'Ernesta','2023-04-17 03:23:33','2023-05-18 21:45:40',	 'Your work opens up new possibilities and opportunities, full of new possibilities'),
       (10,94,'Grier','2022-05-15 00:00:42','2023-05-05 03:31:09',	 'Your work inspires hope for a bright and exciting future, bright and exciting future'),
       (10,95,'Xylina','2023-03-19 07:43:26','2023-05-31 07:35:16',	 'Your work is both futuristic and timeless at the same time, futuristic and timeless'),
       (10,96,'Elsie','2023-02-17 01:12:00','2023-05-24 19:58:49',	 'Your work reflects your incredible vision and foresight, sense of vision and foresight'),
       (10,97,'Oona','2022-11-29 04:56:53','2023-05-08 03:43:04',	 'Your work inspires hope and optimism in viewers, full of hope and optimism'),
       (10,98,'Lynde','2022-03-26 12:32:40','2023-05-04 08:00:29',	 'Your work is bursting with creativity and innovation, bursting with creativity and innovation'),
       (10,99,'Mady','2022-02-28 15:11:48','2023-05-20 11:19:27',	 'Your work is always pushing boundaries and exploring new possibilities, pushing boundaries and exploring new possibilities');

insert into comments(news_id, id, username, create_date_comment, update_date_comment, text)
values (10,100,'Beth','2022-06-26 05:57:57','2023-05-25 04:44:45',	 'Your work is both inspiring and aspirational, inspiring and aspirational'),
       (11,101,'Selma','2022-08-08 22:13:04','2023-05-26 03:32:32',	 'Your work is a true masterpiece, stunning'),
       (11,102,'Rosene','2022-07-19 17:11:46','2023-05-04 10:28:18',	 'You have a remarkable talent for creating beauty, keep it up'),
       (11,103,'Krystle','2022-08-18 20:38:52','2023-05-24 12:37:42',	 'Your work is truly awe-inspiring, breathtaking'),
       (11,104,'Charmaine','2023-01-23 08:30:17','2023-05-06 09:29:29',	 'You have a gift for capturing the essence of your subject, truly impressive'),
       (11,105,'Florencia','2022-05-21 12:48:59','2023-05-08 21:47:05',	 'Your work is both elegant and sophisticated, well done'),
       (11,106,'Carmela','2022-04-17 15:37:11','2023-05-27 00:14:50',	 'Your creative vision is exceptional, keep pushing boundaries'),
       (11,107,'Alex','2022-08-23 08:48:23','2023-05-15 04:10:02',	 'Your work is full of life and energy, vibrant and dynamic'),
       (11,108,'Anthia','2022-03-11 22:42:36','2023-05-22 14:42:37',	 'You have an incredible eye for color, impressive job'),
       (11,109,'Elyssa','2022-02-18 11:31:42','2023-05-07 14:14:11',	 'Your work is both powerful and emotive, evoking strong emotions'),
       (11,110,'Tani','2022-10-04 02:54:19','2023-05-12 19:49:18',	 'Your attention to detail is impeccable, amazing job'),
       (12,111,'Heddie','2022-11-19 03:58:41','2023-05-07 17:38:12',	 'Your work is both thought-provoking and insightful, impressive'),
       (12,112,'Rori','2022-03-05 07:18:28','2023-05-25 20:35:46',	 'You have a talent for creating beauty out of chaos, well done'),
       (12,113,'Britte','2022-09-02 09:28:45','2023-05-09 02:20:52',	 'Your work is both graceful and fluid, impressive job'),
       (12,114,'Camile','2022-02-26 06:31:35','2023-05-18 15:06:50',	 'Your use of light and shadow is masterful, truly impressive'),
       (12,115,'Elena','2022-06-18 02:49:25','2023-05-04 14:41:01',	 'Your work is both bold and daring, impressive job'),
       (12,116,'Flory','2022-02-28 15:23:51','2023-05-03 17:51:03',	 'Your work has a sense of whimsy and playfulness that is delightful, great job'),
       (12,117,'Lucy','2022-06-16 19:32:02','2023-05-16 16:08:18',	 'You have an innate ability to capture the essence of your subject matter, well done'),
       (12,118,'Eolanda','2022-05-20 13:40:37','2023-05-17 10:02:51',	 'Your work has a timeless quality that will never go out of style, impressive job'),
       (12,119,'Edith','2022-09-24 01:49:49','2023-05-03 02:48:39',	 'Your use of texture is both subtle and striking, impressive job'),
       (12,120,'Cam','2022-05-01 05:37:42','2023-05-21 14:20:43',	 'Your work has a sense of movement and flow that is truly captivating, well done'),
       (13,121,'Desirae','2023-03-02 16:33:10','2023-05-16 15:47:42',	 'Your work has a sense of drama and intensity that is truly impressive, great job'),
       (13,122,'Alyda','2023-01-23 12:23:36','2023-05-07 15:22:55',	 'You have an incredible ability to create depth and dimension in your work, well done'),
       (13,123,'Ekaterina','2023-03-08 03:59:26','2023-05-04 12:08:56',	 'Your work has a sense of mystery and intrigue that is truly captivating, impressive job'),
       (13,124,'Amalie','2023-01-01 13:22:04','2023-05-28 16:14:28',	 'Your use of negative space is both striking and effective, impressive job'),
       (13,125,'Ulrike','2022-06-16 14:28:25','2023-05-13 11:36:41',	 'Your work is both powerful and graceful, impressive job'),
       (13,126,'Laurene','2022-03-11 07:22:10','2023-05-21 08:54:52',	 'Your work has a sense of harmony and balance that is truly remarkable, great job'),
       (13,127,'Ursulina','2022-01-09 13:34:42','2023-05-05 08:11:52',	 'Your use of color is both bold and sophisticated, impressive job'),
       (13,128,'Karena','2022-10-03 15:57:06','2023-05-12 22:12:16',	 'Your work has a sense of authenticity and originality that is truly inspiring, well done'),
       (13,129,'Maurene','2023-04-20 17:38:47','2023-05-08 02:20:01',	 'Your work has a sense of whimsy and imagination that is truly delightful, impressive job'),
       (13,130,'Queenie','2022-04-30 00:00:25','2023-05-18 00:32:22',	 'You have a talent for creating beauty out of simplicity, well done'),
       (14,131,'Lorne','2022-02-22 02:18:17','2023-05-21 23:19:45',	 'Your work has a sense of nostalgia and sentimentality that is truly touching, impressive job'),
       (14,132,'Minda','2022-04-13 11:17:52','2023-05-08 11:20:09',	 'Your use of composition is both powerful and effective, great job'),
       (14,133,'Sallie','2022-06-13 23:21:00','2023-05-18 14:08:25',	 'Your work has a sense of wonder and magic that is truly enchanting, impressive job'),
       (14,134,'Clarice','2022-06-08 08:58:16','2023-05-29 21:40:25',	 'Your use of contrast is both striking and effective, well done'),
       (14,135,'Hayley','2022-07-13 09:08:48','2023-05-29 15:25:52',	 'Your work has a sense of elegance and refinement that is truly impressive, great job'),
       (14,136,'Rebeca','2022-05-25 07:38:20','2023-06-01 00:38:14',	 'Your use of pattern is both subtle and striking, impressive job'),
       (14,137,'Clementine','2022-07-31 23:02:02','2023-05-22 22:05:59',	 'Your work has a sense of depth and complexity that is truly remarkable, well done'),
       (14,138,'Daryl','2022-03-06 23:57:02','2023-05-03 03:25:50',	 'Your use of line is both bold and graceful, impressive job'),
       (14,139,'Maryellen','2023-02-18 00:37:01','2023-05-13 03:20:31',	 'Your work has a sense of emotion and heart that is truly moving, great job'),
       (14,140,'Harmonia','2022-10-04 01:31:57','2023-05-07 08:36:03',	 'Your use of shape is both powerful and effective, well done'),
       (15,141,'Collen','2023-01-18 22:09:01','2023-05-15 01:19:04',	 'Your work has a sense of drama and intensity that is truly captivating, impressive job'),
       (15,142,'Hyacinthe','2022-12-21 13:18:44','2023-05-10 15:56:00',	 'Your use of texture is both subtle and striking, great job'),
       (15,143,'Vita','2023-04-22 21:47:58','2023-05-24 15:05:19',	 'Your work has a sense of movement and flow that is truly captivating, well done'),
       (15,144,'Tabbatha','2022-05-31 17:11:10','2023-05-04 15:08:38',	 'Your use of negative space is both striking and effective, impressive job'),
       (15,145,'Misha','2022-11-04 05:49:34','2023-05-26 21:18:32',	 'Your work has a sense of authenticity and originality that is truly inspiring, great job'),
       (15,146,'Molli','2022-06-15 02:11:07','2023-05-23 12:30:32',	 'Your use of color is both bold and sophisticated, impressive job'),
       (15,147,'Fernande','2022-07-11 17:26:15','2023-05-07 12:45:49',	 'Your work has a sense of whimsy and imagination that is truly delightful, well done'),
       (15,148,'Nelle','2022-03-16 19:03:23','2023-05-12 04:16:48',	 'You have a talent for creating beauty out of simplicity, impressive job'),
       (15,149,'Roxane','2022-07-22 06:44:07','2023-05-16 03:23:24',	 'Your work has a sense of nostalgia and sentimentality that is truly touching, great job'),
       (15,150,'Britni','2023-01-25 10:07:27','2023-05-28 04:49:20',	 'Your use of composition is both powerful and effective, well done'),
       (16,151,'Sheelagh','2022-08-06 07:59:22','2023-05-27 22:34:28',	 'Your work has a sense of wonder and magic that is truly enchanting, impressive job'),
       (16,152,'Joy','2022-02-16 06:25:57','2023-05-19 07:11:55',	 'Your use of contrast is both striking and effective, great job'),
       (16,153,'Wanda','2023-04-29 11:40:08','2023-05-24 08:04:03',	 'Your work has a sense of elegance and refinement that is truly impressive, we ll done'),
       (16,154,'Jaclyn','2023-04-21 12:06:01','2023-05-23 21:22:54',	 'Your use of pattern is both subtle and striking, impressive job'),
       (16,155,'Danika','2022-07-18 20:49:37','2023-05-16 19:06:04',	 'Your work has a sense of depth and complexity that is truly remarkable, great job'),
       (16,156,'Aeriela','2022-07-15 09:48:51','2023-05-13 02:27:15',	 'Your use of line is both bold and graceful, well done'),
       (16,157,'Consuela','2022-05-27 06:11:53','2023-05-17 10:34:57',	 'Your work has a sense of emotion and heart that is truly moving, impressive job'),
       (16,158,'Gui','2022-05-27 16:11:22','2023-05-18 06:56:43',	 'Your use of shape is both powerful and effective, great job'),
       (16,159,'Marinna','2023-04-28 16:40:35','2023-05-12 11:21:40',	 'Your work has a sense of drama and intensity that is truly captivating, well done'),
       (16,160,'Katuscha','2023-04-25 09:31:33','2023-05-14 19:03:35',	 'Your use of texture is both subtle and striking, impressive job'),
       (17,161,'Priscilla','2022-05-14 21:44:51','2023-05-02 06:57:35',	 'Your work has a sense of movement and flow that is truly captivating, great job'),
       (17,162,'Blinni','2023-02-28 22:33:35','2023-05-11 02:55:01',	 'Your use of negative space is both striking and effective, well done'),
       (17,163,'Ileana','2023-02-25 20:10:14','2023-05-15 02:31:08',	 'Your work has a sense of authenticity and originality that is truly inspiring, impressive job'),
       (17,164,'Merci','2022-03-26 11:47:23','2023-05-20 16:10:32',	 'Your use of color is both bold and sophisticated, great job'),
       (17,165,'Lizzie','2022-03-28 14:26:50','2023-05-09 03:32:20',	 'Your work has a sense of whimsy and imagination that is truly delightful, well done'),
       (17,166,'Rozele','2022-05-30 15:39:07','2023-05-25 09:13:04',	 'You have a talent for creating beauty out of simplicity, impressive job'),
       (17,167,'Latisha','2022-12-05 13:16:28','2023-05-13 03:06:33',	 'Your work has a sense of nostalgia and sentimentality that is truly touching, great job'),
       (17,168,'Theodora','2022-03-21 18:11:33','2023-05-06 17:47:59',	 'Your use of composition is both powerful and effective, impressive job'),
       (17,169,'Lory','2022-01-03 20:25:38','2023-05-10 14:08:40',	 'Your work has a sense of wonder and magic that is truly enchanting, well done'),
       (17,170,'Darlleen','2023-01-09 15:57:21','2023-05-26 11:20:44',	 'Your use of contrast is both striking and effective, impressive job'),
       (18,171,'Berget','2022-02-08 12:08:49','2023-05-25 23:33:49',	 'Your work has a sense of elegance and refinement that is truly impressive, great job'),
       (18,172,'Gwyneth','2022-06-06 19:23:02','2023-05-23 20:37:03',	 'Your use of pattern is both subtle and striking, well done'),
       (18,173,'Netty','2023-04-15 12:33:34','2023-05-09 12:10:54',	 'Your work has a sense of depth and complexity that is truly remarkable, impressive job'),
       (18,174,'Dale','2023-02-13 12:23:55','2023-05-29 14:29:24',	 'Your use of line is both bold and graceful, great job'),
       (18,175,'Robbi','2022-12-27 17:12:47','2023-05-06 03:21:38',	 'Your work has a sense of emotion and heart that is truly moving, well done'),
       (18,176,'Tersina','2022-11-28 06:49:40','2023-05-20 18:07:07',	 'Your use of shape is both powerful and effective, impressive job'),
       (18,177,'Robinia','2023-04-17 11:57:56','2023-05-29 08:05:14',	 'Your work has a sense of drama and intensity that is truly captivating, great job'),
       (18,178,'Daryl','2023-01-27 17:43:54','2023-05-15 13:19:55',	 'Your use of texture is both subtle and striking, well done'),
       (18,179,'Kathy','2022-08-22 01:18:58','2023-05-05 03:04:58',	 'Your work has a sense of movement and flow that is truly captivating, impressive job'),
       (18,180,'Keelia','2023-01-17 16:37:58','2023-05-03 20:19:10',	 'Your use of negative space is both striking and effective, great job'),
       (19,181,'Gretal','2023-01-03 22:25:57','2023-05-10 06:14:33',	 'Your work has a sense of authenticity and originality that is truly inspiring, well done'),
       (19,182,'Rubie','2022-08-27 06:21:20','2023-05-16 07:00:44',	 'Your use of color is both bold and sophisticated, impressive job'),
       (19,183,'Gretal','2022-06-22 08:02:29','2023-05-12 20:00:32',	 'Your work has a sense of whimsy and imagination that is truly delightful, great job'),
       (19,184,'Ivett','2023-03-28 17:49:51','2023-05-30 09:56:07',	 'You have a talent for creating beauty out of simplicity, well done'),
       (19,185,'Heida','2022-12-05 19:18:09','2023-05-04 12:22:37',	 'Your work has a sense of nostalgia and sentimentality that is truly touching, impressive job'),
       (19,186,'Caryl','2022-01-28 16:10:33','2023-05-06 06:00:35',	 'Your use of composition is both powerful and effective, great job'),
       (19,187,'Kaja','2022-10-29 15:54:44','2023-05-23 19:31:48',	 'Your work has a sense of wonder and magic that is truly enchanting, impressive job'),
       (19,188,'Roberta','2022-05-07 10:18:19','2023-05-31 19:43:06',	 'Your use of contrast is both striking and effective, well done'),
       (19,189,'Louella','2023-03-13 14:22:45','2023-05-23 04:54:00',	 'Your work has a sense of elegance and refinement that is truly impressive, impressive job'),
       (19,190,'Iseabal','2022-03-12 17:21:16','2023-05-13 01:46:25',	 'Your use of pattern is both subtle and striking, great job'),
       (20,191,'Nannie','2022-03-03 02:52:59','2023-05-20 02:15:41',	 'Your work has a sense of depth and complexity that is truly remarkable, well done'),
       (20,192,'Betta','2022-11-20 16:41:20','2023-05-12 07:03:14',	 'Your use of line is both bold and graceful, impressive job'),
       (20,193,'Taffy','2022-09-22 13:19:06','2023-05-19 21:04:31',	 'Your work has a sense of emotion and heart that is truly moving, great job'),
       (20,194,'Juliane','2023-02-20 21:37:07','2023-05-24 06:54:14',	 'Your use of shape is both powerful and effective, well done'),
       (20,195,'Albertina','2022-03-07 17:38:56','2023-05-20 05:37:31',	 'Your work has a sense of drama and intensity that is truly captivating, impressive job'),
       (20,196,'Diena','2023-01-08 02:00:55','2023-05-22 02:32:16',	 'Your use of texture is both subtle and striking, great job'),
       (20,197,'Deirdre','2022-02-05 22:05:28','2023-05-29 00:19:27',	 'Your work has a sense of movement and flow that is truly captivating, well done'),
       (20,198,'Dulcinea','2022-12-12 17:38:46','2023-05-14 22:17:55',	 'Your use of negative space is both striking and effective, impressive job'),
       (20,199,'Beatriz','2022-12-25 09:49:54','2023-05-25 03:54:55',	 'Your work has a sense of authenticity and originality that is truly inspiring, great job'),
       (20,200,'Marsiella','2022-07-04 19:20:59','2023-05-07 15:37:54',	 'Your use of color is both bold and sophisticated, well done')







